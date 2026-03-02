package de.riversroses.strategy;

import de.riversroses.config.StrategyConfig;
import de.riversroses.infra.client.GameServerClient;
import de.riversroses.planet.business.RegistrationService;
import de.riversroses.scan.dto.RadarScanResponseDto;
import de.riversroses.scan.dto.RadarScanResponseDto.FoundResource;
import de.riversroses.ship.dto.SetCourseRequestDto;
import de.riversroses.ship.dto.ShipStatusDto;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Singleton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Singleton
@Slf4j
public class PatrolStrategyService {

    private final GameServerClient gameClient;
    private final RegistrationService registrationService;
    private final StrategyConfig config;

    private static final int CARGO_LIMIT = 500;
    private static final double ESTIMATED_SPEED = 100.0;
    
    // PHYSICS CONSTANT: The game collects everything within 15.0 units
    // We use 14.5 to be safe against floating point errors
    private static final double COLLECTION_RADIUS = 14.5; 

    // STATE: Maps ShipID -> List of Resource IDs it is currently targeting
    private final Map<String, ActiveMission> shipMissions = new ConcurrentHashMap<>();

    public PatrolStrategyService(GameServerClient gameClient,
                                 RegistrationService registrationService,
                                 StrategyConfig config) {
        this.gameClient = gameClient;
        this.registrationService = registrationService;
        this.config = config;
    }

    @Scheduled(fixedDelay = "1s") 
    public void tick() {
        String token = registrationService.token();
        if (token == null) return;

        try {
            List<ShipStatusDto> myShips = gameClient.myShips(token);
            if (myShips.isEmpty()) return;

            cleanupStaleMissions(myShips);

            for (ShipStatusDto ship : myShips) {
                processShipStrategy(token, ship);
            }

        } catch (Exception e) {
            log.warn("Strategy tick failed: ", e);
        }
    }

    private void cleanupStaleMissions(List<ShipStatusDto> currentShips) {
        Set<String> activeShipIds = currentShips.stream()
                .map(ShipStatusDto::shipId)
                .collect(Collectors.toSet());
        shipMissions.keySet().removeIf(id -> !activeShipIds.contains(id));
    }

    private void processShipStrategy(String token, ShipStatusDto me) {
        if (isBusyExecuting(me)) return;

        // 1. Identify locked resources (targeted by OTHER ships)
        Set<String> globalBusyResources = new HashSet<>();
        for (Map.Entry<String, ActiveMission> entry : shipMissions.entrySet()) {
            if (!entry.getKey().equals(me.shipId())) {
                globalBusyResources.addAll(entry.getValue().targetResourceIds);
            }
        }

        var cargoMap = me.cargo() != null ? me.cargo() : Collections.<String, Integer>emptyMap();
        long currentCargo = cargoMap.values().stream().mapToInt(Integer::intValue).sum();

        RadarScanResponseDto scan = gameClient.scan(token, me.shipId());
        
        // Filter out resources already targeted by friends
        List<FoundResource> availableResources = scan.resources().stream()
                .filter(r -> !globalBusyResources.contains(r.id()))
                .collect(Collectors.toList());

        if (currentCargo >= CARGO_LIMIT || (currentCargo > 0 && availableResources.isEmpty())) {
            returnToBase(token, me);
        } else {
            performClusterMining(token, me, availableResources, currentCargo);
        }
    }

    private boolean isBusyExecuting(ShipStatusDto me) {
        if (!shipMissions.containsKey(me.shipId())) return false;

        ActiveMission mission = shipMissions.get(me.shipId());
        double dist = distance(me.x(), me.y(), mission.targetX, mission.targetY);

        // If we are close enough to the Calculated Cluster Center, we are done.
        // The server will auto-collect the rocks.
        if (dist < 5.0) {
            return false;
        }
        return true;
    }

    private void performClusterMining(String token, ShipStatusDto me, List<FoundResource> resources, long currentCargo) {
        if (resources.isEmpty()) {
            patrol(token, me);
            return;
        }

        double fullnessRatio = (double) currentCargo / CARGO_LIMIT;
        double homeX = 500;
        double homeY = 500;

        TargetSolution bestSolution = null;
        double bestScore = Double.NEGATIVE_INFINITY;

        // --- CLUSTERING ALGORITHM ---
        // For every resource, treat it as a potential "anchor" for a cluster
        for (FoundResource anchor : resources) {
            
            // 1. Find all neighbors that COULD theoretically fit in one scoop (dist <= 30)
            List<FoundResource> neighbors = new ArrayList<>();
            neighbors.add(anchor);
            
            for (FoundResource other : resources) {
                if (other == anchor) continue;
                if (distance(anchor.x(), anchor.y(), other.x(), other.y()) <= (COLLECTION_RADIUS * 2)) {
                    neighbors.add(other);
                }
            }

            // 2. Calculate the "Center of Mass" (Geometric Center) of these neighbors
            //    This is a heuristic. A true "Smallest Enclosing Circle" is harder, but Avg is fast/good enough.
            double sumX = 0;
            double sumY = 0;
            for (FoundResource n : neighbors) {
                sumX += n.x();
                sumY += n.y();
            }
            double centerX = sumX / neighbors.size();
            double centerY = sumY / neighbors.size();

            // 3. Verify: Which of these neighbors ACTUALLY fit in a 15px radius from that center?
            List<String> validIds = new ArrayList<>();
            double clusterValue = 0;
            
            for (FoundResource n : neighbors) {
                if (distance(centerX, centerY, n.x(), n.y()) <= COLLECTION_RADIUS) {
                    validIds.add(n.id());
                    clusterValue += n.value();
                }
            }

            // 4. Score this cluster solution
            double distToCluster = distance(me.x(), me.y(), centerX, centerY);
            double time = (distToCluster / ESTIMATED_SPEED) + 1.0;
            
            double distToHome = distance(centerX, centerY, homeX, homeY);
            double homePenalty = fullnessRatio * (distToHome / 10.0);

            // SCORE = (Total Cluster Value / Time) - Penalty
            double score = (clusterValue / time) - homePenalty;

            if (score > bestScore) {
                bestScore = score;
                bestSolution = new TargetSolution(validIds, centerX, centerY, clusterValue);
            }
        }

        if (bestSolution != null) {
            log.info("{} >> Targeting Cluster ({} items, Val: {}). Loc: {},{}", 
                    me.displayName(), bestSolution.ids.size(), (int)bestSolution.value, 
                    (int)bestSolution.targetX, (int)bestSolution.targetY);

            gameClient.setCourse(token, new SetCourseRequestDto(me.shipId(), bestSolution.targetX, bestSolution.targetY));

            // Lock ALL ids in this cluster so the other ship ignores them
            shipMissions.put(me.shipId(), new ActiveMission(new HashSet<>(bestSolution.ids), bestSolution.targetX, bestSolution.targetY));
        } else {
            patrol(token, me);
        }
    }

    private void returnToBase(String token, ShipStatusDto me) {
        double homeX = registrationService.getHomeX();
        double homeY = registrationService.getHomeY();
        gameClient.setCourse(token, new SetCourseRequestDto(me.shipId(), homeX, homeY));
        shipMissions.put(me.shipId(), new ActiveMission(Collections.emptySet(), homeX, homeY));
    }

    private void patrol(String token, ShipStatusDto me) {
        double targetX = 500 + (Math.random() - 0.5) * 600;
        double targetY = 500 + (Math.random() - 0.5) * 600;
        gameClient.setCourse(token, new SetCourseRequestDto(me.shipId(), targetX, targetY));
        shipMissions.put(me.shipId(), new ActiveMission(Collections.emptySet(), targetX, targetY));
    }

    private double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    @Data
    @AllArgsConstructor
    private static class ActiveMission {
        Set<String> targetResourceIds; // The list of rocks we are about to scoop
        double targetX;
        double targetY;
    }

    @Data
    @AllArgsConstructor
    private static class TargetSolution {
        List<String> ids;
        double targetX;
        double targetY;
        double value;
    }
}