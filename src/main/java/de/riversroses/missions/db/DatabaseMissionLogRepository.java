package de.riversroses.missions.db;

import de.riversroses.missions.model.MissionLog;
import de.riversroses.missions.model.MissionStatus;

import java.util.List;
import java.util.Optional;

public class DatabaseMissionLogRepository {


    public List<MissionLog> findByStatusOrderByCreatedAtDesc(MissionStatus status) {
        return List.of();
    }

    public Optional<MissionLog> findByMissionId(String missionId) {
        return Optional.empty();
    }

    public void save(MissionLog missionLog) {

    }

    public void update(MissionLog log) {
    }

}
