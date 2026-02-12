package de.riversroses.missions.db;

import de.riversroses.missions.model.MissionLog;
import de.riversroses.missions.model.MissionStatus;
import jakarta.inject.Singleton;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Singleton
public class InMemoryMissionLogRepository implements MissionLogRepository {

    private final HashMap<String, MissionLog> missions;

    public InMemoryMissionLogRepository() {
        this.missions = new HashMap<>();
    }

    @Override
    public List<MissionLog> findByStatusOrderByCreatedAtDesc(MissionStatus status) {
        return missions.values().stream().filter(mission -> mission.getStatus().equals(status)).toList();
    }

    public Optional<MissionLog> findByMissionId(String missionId) {
        return Optional.ofNullable(missions.get(missionId));
    }

    public void save(MissionLog missionLog) {
        missions.put(missionLog.getMissionId(), missionLog);
    }

    public void update(MissionLog missionLog) {
        missions.put(missionLog.getMissionId(), missionLog);
    }
}
