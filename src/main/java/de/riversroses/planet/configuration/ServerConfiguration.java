package de.riversroses.planet.configuration;

import io.micronaut.context.annotation.ConfigurationProperties;

@ConfigurationProperties("server")
public record ServerConfiguration(String ip) {
}
