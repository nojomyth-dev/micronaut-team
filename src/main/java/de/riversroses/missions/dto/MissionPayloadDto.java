package de.riversroses.missions.dto;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Serdeable
@Data
public class MissionPayloadDto {

  public String id;
  
  @NotNull
  public String description;
  
  @NotNull
  public Double x;
  
  @NotNull
  public Double y;
}
