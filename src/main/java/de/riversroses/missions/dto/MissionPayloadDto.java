package de.riversroses.missions.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

@Serdeable
@Data
public class MissionPayloadDto {

  public String id;
  
  public String description;
  
  public Double x;
  
  public Double y;
}
