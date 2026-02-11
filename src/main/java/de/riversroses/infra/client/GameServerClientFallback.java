package de.riversroses.infra.client;

import de.riversroses.scan.dto.RadarScanResponseDto;
import de.riversroses.ship.dto.SetCourseRequestDto;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.retry.annotation.Fallback;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;

@Fallback
@Slf4j
public class GameServerClientFallback implements GameServerClient {

  public void setCourse(String token, SetCourseRequestDto req) {
    log.warn("Fallback: Could not set course for ship {}", req.shipId());
  }

  public RadarScanResponseDto scan(String token, @Nullable String shipId) {
    log.warn("Fallback: Could not scan from ship {}", shipId);
    return new RadarScanResponseDto(Collections.emptyList(), Collections.emptyList());
  }
}
