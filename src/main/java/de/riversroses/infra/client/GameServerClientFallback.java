package de.riversroses.infra.client;

import de.riversroses.scan.dto.RadarScanResponseDto;
import de.riversroses.ship.dto.SetCourseRequestDto;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.retry.annotation.Fallback;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;

public class GameServerClientFallback {

  public void setCourse(String token, SetCourseRequestDto req) {
  }

  public RadarScanResponseDto scan(String token, @Nullable String shipId) {
    return null;
  }
}
