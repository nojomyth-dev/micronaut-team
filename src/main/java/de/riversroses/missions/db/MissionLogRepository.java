package de.riversroses.missions.db;

import de.riversroses.missions.model.MissionLog;
import de.riversroses.missions.model.MissionStatus;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MissionLogRepository {

  List<MissionLog> findByStatusOrderByCreatedAtDesc(MissionStatus status);

  Optional<MissionLog> findByMissionId(String missionId);

  void save(MissionLog missionLog);

  void update(MissionLog log);

  void deleteAll();
}
