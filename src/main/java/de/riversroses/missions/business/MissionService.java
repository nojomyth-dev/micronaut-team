package de.riversroses.missions.business;

import de.riversroses.missions.db.MissionLogRepository;
import de.riversroses.missions.dto.MissionCompletionDto;
import de.riversroses.missions.dto.MissionPayloadDto;
import de.riversroses.missions.model.MissionLog;
import de.riversroses.missions.model.MissionStatus;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@Singleton
public class MissionService {

    private static final Logger log = LoggerFactory.getLogger(MissionService.class);

    private final MissionLogRepository repo;
    private final MissionRng rng;

    protected double worldWidth = 1000;

    protected double worldHeight = 1000;

    public MissionService(MissionLogRepository repo, MissionRng rng) {
        this.repo = repo;
        this.rng = rng;
    }

    public MissionPayloadDto generateOrReuseMission() {
        List<MissionLog> pending = repo.findByStatusOrderByCreatedAtDesc(MissionStatus.PENDING);
        if (!pending.isEmpty()) {
            return toDto(pending.get(0));
        }

        String missionId = "mission-" + UUID.randomUUID();
        double x = rng.coordinate(worldWidth);
        double y = rng.coordinate(worldHeight);

        MissionLog missionLog = new MissionLog(missionId, "Explore the system near (" + (int) x + "," + (int) y + ")", x, y);
        repo.save(missionLog);
        log.info("A new mission has been generated: {}", missionLog);
        return toDto(missionLog);
    }

    public void markCompleted(MissionCompletionDto dto) {
        repo.findByMissionId(dto.missionId)
            .ifPresent(log -> {
                log.complete(dto.shipId, dto.teamId);
                repo.update(log);
            });
    }

    private MissionPayloadDto toDto(MissionLog log) {
        MissionPayloadDto dto = new MissionPayloadDto();
        dto.id = log.getMissionId();
        dto.description = log.getDescription();
        dto.x = log.getX();
        dto.y = log.getY();
        return dto;
    }
}
