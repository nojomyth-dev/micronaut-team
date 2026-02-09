package de.riversroses.ship.dto;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;

@Serdeable
public record RegisterShipRequestDto(
    @NotBlank String shipName) {
}
