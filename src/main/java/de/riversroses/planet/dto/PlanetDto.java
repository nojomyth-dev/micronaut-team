package de.riversroses.planet.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record PlanetDto(String teamName) {
  
}
