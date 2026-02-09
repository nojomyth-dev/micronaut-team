package de.riversroses.missions.dto;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Serdeable
@Data
public class MissionCompletionDto {

  @NotBlank
  public String missionId;
  
  @NotBlank
  public String shipId;
  
  @NotBlank
  public String teamId;

  @Min(0)
  public Integer reward;
}
