package de.riversroses.planet.configuration;

import io.micronaut.context.annotation.ConfigurationProperties;

@ConfigurationProperties("team")
public record TeamConfiguration(String name) {
}
