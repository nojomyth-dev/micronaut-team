package de.riversroses.missions.db;

import de.riversroses.missions.model.MissionLog;
import de.riversroses.missions.model.MissionStatus;
import io.micronaut.context.annotation.Primary;
import jakarta.inject.Singleton;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Singleton
@Primary
public class InMemoryMissionLogRepository implements MissionLogRepository {

    private final HashMap<String, MissionLog> missions;

    public InMemoryMissionLogRepository() {
        this.missions = new HashMap<>();
    }

    @Override
    public List<MissionLog> findByStatusOrderByCreatedAtDesc(MissionStatus status) {
        return missions.values().stream().filter(mission -> mission.getStatus().equals(status)).toList();
    }

    @Override
    public Optional<MissionLog> findByMissionId(String missionId) {
        return Optional.ofNullable(missions.get(missionId));
    }

    @Override
    public void save(MissionLog missionLog) {
        missions.put(missionLog.getMissionId(), missionLog);
    }

    @Override
    public void update(MissionLog missionLog) {
        missions.put(missionLog.getMissionId(), missionLog);
    }

    @Override
    public void deleteAll() {
        missions.clear();
    }

}
