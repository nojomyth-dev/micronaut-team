package de.riversroses.missions.db;

import de.riversroses.missions.model.MissionLog;
import de.riversroses.missions.model.MissionStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class InMemoryMissionLogRepository {

    private final HashMap<Long, MissionLog> missions;

    public InMemoryMissionLogRepository() {
        this.missions = new HashMap<>();
    }

    public List<MissionLog> findByStatusOrderByCreatedAtDesc(MissionStatus status) {
        return missions.values().stream().toList();
    }

    public Optional<MissionLog> findByMissionId(String missionId) {
        return Optional.ofNullable(missions.get(Long.parseLong(missionId)));
    }

    public void save(MissionLog missionLog) {
        missions.put(missionLog.getId(), missionLog);
    }

    public void update(MissionLog missionLog) {
        missions.put(missionLog.getId(), missionLog);
    }
}
