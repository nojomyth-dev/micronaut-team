package de.riversroses.missions.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record MissionDto(long id, String name, String description, @Min(0) int credits) {
  
}
