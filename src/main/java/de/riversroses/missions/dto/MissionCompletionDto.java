package de.riversroses.missions.dto;

import lombok.Data;

@Data
public class MissionCompletionDto {

  public String missionId;
  
  public String shipId;
  
  public String teamId;

  public Integer reward;
}
