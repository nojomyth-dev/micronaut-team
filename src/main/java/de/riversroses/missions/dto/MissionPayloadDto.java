package de.riversroses.missions.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class MissionPayloadDto {

  public String id;
  
  public String description;
  
  public Double x;
  
  public Double y;
}
