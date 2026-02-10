package de.riversroses.missions.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class MissionCompletionDto {

  public String missionId;
  
  public String shipId;
  
  public String teamId;

  public Integer reward;
}
