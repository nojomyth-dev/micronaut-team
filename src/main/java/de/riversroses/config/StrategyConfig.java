package de.riversroses.config;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.core.annotation.Introspected;

@ConfigurationProperties("strategy")
@Introspected
public record StrategyConfig(
    Double homeBias) {

  public double homeBiasOrDefault() {
    return homeBias != null ? homeBias : 0.5;
  }
}
