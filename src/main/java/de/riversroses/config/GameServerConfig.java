package de.riversroses.config;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.NotBlank;

@ConfigurationProperties("game-server")
@Introspected
public record GameServerConfig(
    @NotBlank String baseUrl) {
}
