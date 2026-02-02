package de.riversroses.missions.business;

import java.util.Collection;

import de.riversroses.missions.db.InMemoryMissionRepository;
import de.riversroses.missions.db.MissionRepository;
import de.riversroses.missions.dto.MissionDto;
import io.micronaut.context.annotation.Prototype;
import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Prototype
public class MissionService {
  
  private final MissionRepository missionRepository;

  public MissionService(InMemoryMissionRepository missionRepository) {
    this.missionRepository = missionRepository;
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
