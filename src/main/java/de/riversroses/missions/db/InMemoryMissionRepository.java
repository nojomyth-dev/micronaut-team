package de.riversroses.missions.db;

import java.util.HashMap;

import de.riversroses.missions.dto.MissionDto;
import io.micronaut.context.annotation.Prototype;
import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Prototype
public class InMemoryMissionRepository implements MissionRepository {
  
  private final HashMap<Long, MissionDto> missions;

  public InMemoryMissionRepository() {
    this.missions = new HashMap<>();
  }

  public boolean removeMission(long missionId) {
    return missions.remove(missionId) != null;
  }

  public void addMission(Long id, MissionDto mission) {
    missions.put(id, mission);
  }

  @Override
  public HashMap<Long, MissionDto> getMissions() {
    return missions;
  }
}
