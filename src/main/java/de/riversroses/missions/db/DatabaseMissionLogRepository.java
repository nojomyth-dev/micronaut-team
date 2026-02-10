package de.riversroses.missions.db;

import de.riversroses.missions.model.MissionLog;
import de.riversroses.missions.model.MissionStatus;

import java.util.List;
import java.util.Optional;

public class DatabaseMissionLogRepository {
    private final JpaMissionLogRepository jpaMissionLogRepository;

    public DatabaseMissionLogRepository(JpaMissionLogRepository jpaMissionLogRepository) {
        this.jpaMissionLogRepository = jpaMissionLogRepository;
    }

    public List<MissionLog> findByStatusOrderByCreatedAtDesc(MissionStatus status) {
        return jpaMissionLogRepository.findByStatusOrderByCreatedAtDesc(status);
    }

    public Optional<MissionLog> findByMissionId(String missionId) {
        return jpaMissionLogRepository.findByMissionId(missionId);
    }

    public void save(MissionLog missionLog) {
        jpaMissionLogRepository.save(missionLog);
    }

    public void update(MissionLog log) {
        jpaMissionLogRepository.update(log);
    }

}
