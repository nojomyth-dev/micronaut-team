package de.riversroses.missions.business;

import de.riversroses.missions.db.InMemoryMissionRepository;
import de.riversroses.missions.db.MissionRepository;
import de.riversroses.missions.dto.MissionDto;
import jakarta.inject.Singleton;

import java.util.Collection;

@Singleton
public class MissionService {
  
  private final MissionRepository missionRepository;

  // nach DI
  public MissionService(InMemoryMissionRepository missionRepository) {
    this.missionRepository = missionRepository;
  }

  // vor DI
  public MissionService() {
    this.missionRepository = new InMemoryMissionRepository();
  }

  public void addMission() {
    missionRepository.addMission(5L, new MissionDto(1L, "new one", "new one", 1));
  }

  public boolean removeMission(long missionId) {
    return missionRepository.removeMission(missionId);
  }

  public Collection<MissionDto> getMissions() {
    return missionRepository.getMissions().values();
  }
}
