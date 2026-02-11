package de.riversroses.missions.db;

import de.riversroses.missions.model.MissionLog;
import de.riversroses.missions.model.MissionStatus;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class DatabaseMissionLogRepository implements MissionLogRepository {
    private final JpaMissionLogRepository jpaMissionLogRepository;

    public DatabaseMissionLogRepository(JpaMissionLogRepository jpaMissionLogRepository) {
        this.jpaMissionLogRepository = jpaMissionLogRepository;
    }

    @Override
    public List<MissionLog> findByStatusOrderByCreatedAtDesc(MissionStatus status) {
        return jpaMissionLogRepository.findByStatusOrderByCreatedAtDesc(status);
    }

    @Override
    public Optional<MissionLog> findByMissionId(String missionId) {
        return jpaMissionLogRepository.findByMissionId(missionId);
    }

    @Override
    public void save(MissionLog missionLog) {
        jpaMissionLogRepository.save(missionLog);
    }

    @Override
    public void update(MissionLog log) {
        jpaMissionLogRepository.update(log);
    }

    @Override
    public void deleteAll() {
        jpaMissionLogRepository.deleteAll();
    }
}
