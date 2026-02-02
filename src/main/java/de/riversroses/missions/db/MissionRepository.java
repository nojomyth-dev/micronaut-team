package de.riversroses.missions.db;

import java.util.HashMap;

import de.riversroses.missions.dto.MissionDto;

public interface MissionRepository {
  
  public boolean removeMission(long missionId);
  public void addMission(Long id, MissionDto mission);
  public HashMap<Long, MissionDto> getMissions();
}
