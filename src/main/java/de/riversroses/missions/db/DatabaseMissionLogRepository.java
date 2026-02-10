package de.riversroses.missions.db;

import de.riversroses.missions.model.MissionLog;
import de.riversroses.missions.model.MissionStatus;
import io.micronaut.context.annotation.Primary;
import io.micronaut.context.annotation.Requires;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Singleton
@Requires(beans = JPAMissionLogRepository.class)
@Primary
public class DatabaseMissionLogRepository implements MissionLogRepository {

    private static final Logger log = LoggerFactory.getLogger(DatabaseMissionLogRepository.class);


    public List<MissionLog> findByStatusOrderByCreatedAtDesc(MissionStatus status) {
        log.warn("not implented");
        return List.of();
    }

    public Optional<MissionLog> findByMissionId(String missionId) {
        log.warn("not implented");
        return Optional.empty();
    }

    public void save(MissionLog missionLog) {
        log.warn("not implented");

    }

    public void update(MissionLog missionLog) {
        log.warn("not implented");
    }

}
