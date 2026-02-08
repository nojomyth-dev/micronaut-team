package de.riversroses.infra.client;

import de.riversroses.planet.dto.RegisterShipResponseDto;
import de.riversroses.planet.dto.RegisterTeamRequestDto;
import de.riversroses.scan.dto.RadarScanResponseDto;
import de.riversroses.ship.dto.RegisterShipRequestDto;
import de.riversroses.ship.dto.SetCourseRequestDto;
import de.riversroses.ship.dto.ShipStatusDto;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.retry.annotation.Fallback;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

@Fallback
@Slf4j
public class GameServerClientFallback implements GameServerClient {

  @Override
  public RegisterShipResponseDto registerTeam(RegisterTeamRequestDto request) {
    log.warn("Fallback: Register Team failed.");
    return null;
  }

  @Override
  public ShipStatusDto registerShip(String token, RegisterShipRequestDto request) {
    log.warn("Fallback: Register Ship failed.");
    return null;
  }

  @Override
  public List<ShipStatusDto> myShips(String token) {
    log.warn("Fallback: Could not fetch ships.");
    return Collections.emptyList();
  }

  @Override
  public void setCourse(String token, SetCourseRequestDto req) {
    log.warn("Fallback: Could not set course for ship {}", req.shipId());
  }

  @Override
  public RadarScanResponseDto scan(String token, @Nullable String shipId) {
    log.warn("Fallback: Could not scan from ship {}", shipId);
    return new RadarScanResponseDto(Collections.emptyList(), Collections.emptyList());
  }
}
