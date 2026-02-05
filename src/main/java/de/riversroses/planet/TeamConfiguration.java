package de.riversroses.planet;

import io.micronaut.context.annotation.ConfigurationProperties;

@ConfigurationProperties("team")
public record TeamConfiguration(String name) {
}
