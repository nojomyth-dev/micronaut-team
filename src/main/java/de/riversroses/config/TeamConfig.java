package de.riversroses.config;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.NotBlank;

@ConfigurationProperties("team")
@Introspected
public record TeamConfig(
    @NotBlank String token,
    @NotBlank String name,
    @NotBlank String planetName) {
}
