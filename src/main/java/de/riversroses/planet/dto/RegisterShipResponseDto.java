package de.riversroses.planet.dto;
import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Serdeable
public record RegisterShipResponseDto(
    String teamId,
    String planetId,
    double planetX,
    double planetY,
    List<String> shipIds) {
}
