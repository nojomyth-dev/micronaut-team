package de.riversroses.planet.dto;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;

@Serdeable
public record RegisterTeamRequestDto(
    @NotBlank String token,
    @NotBlank String teamName,
    @NotBlank String planetName) {
}
