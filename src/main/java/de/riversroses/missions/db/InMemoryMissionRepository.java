package de.riversroses.missions.db;

import de.riversroses.missions.dto.MissionDto;
import io.micronaut.context.annotation.Prototype;

import java.util.HashMap;

@Prototype
public class InMemoryMissionRepository implements MissionRepository {
  
  private final HashMap<Long, MissionDto> missions;

  public InMemoryMissionRepository() {
    this.missions = new HashMap<>();
  }

  @Override
  public boolean removeMission(long missionId) {
    return missions.remove(missionId) != null;
  }

  @Override
  public void addMission(Long id, MissionDto mission) {
    missions.put(id, mission);
  }

  @Override
  public HashMap<Long, MissionDto> getMissions() {
    return missions;
  }
}
