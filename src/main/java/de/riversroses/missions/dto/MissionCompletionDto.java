package de.riversroses.missions.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

@Serdeable
@Data
public class MissionCompletionDto {

  public String missionId;
  
  public String shipId;
  
  public String teamId;

  public Integer reward;
}
