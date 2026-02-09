package de.riversroses.scan.dto;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Serdeable
public record RadarScanResponseDto(
    List<FoundShip> ships,
    List<FoundResource> resources) {

  @Serdeable
  public static record FoundShip(
      String shipId,
      String teamName,
      double x,
      double y,
      double speed,
      double heading) {
  }

  @Serdeable
  public static record FoundResource(
      String id,
      String oreId,
      int value,
      double x,
      double y) {
  }
}
