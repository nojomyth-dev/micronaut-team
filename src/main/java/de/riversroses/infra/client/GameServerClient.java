package de.riversroses.infra.client;

import de.riversroses.planet.dto.RegisterTeamRequestDto;
import de.riversroses.planet.dto.RegisterShipResponseDto;
import de.riversroses.ship.dto.RegisterShipRequestDto;
import de.riversroses.ship.dto.SetCourseRequestDto;
import de.riversroses.ship.dto.ShipStatusDto;
import de.riversroses.scan.dto.RadarScanResponseDto;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.CircuitBreaker;

import java.util.List;

@Client(id = "game-server")
@CircuitBreaker(delay = "2s", attempts = "3", reset = "30s")
public interface GameServerClient {

  @Post("/ships/teams/register")
  RegisterShipResponseDto registerTeam(@Body RegisterTeamRequestDto request);

  @Post("/ships/register")
  ShipStatusDto registerShip(@Header("X-Token") String token, @Body RegisterShipRequestDto request);

  @Get("/ships/me")
  List<ShipStatusDto> myShips(@Header("X-Token") String token);

  @Post("/ships/course")
  void setCourse(@Header("X-Token") String token, @Body SetCourseRequestDto req);

  @Get("/scan{?shipId}")
  RadarScanResponseDto scan(
      @Header("X-Token") String token, 
      @Nullable @QueryValue("shipId") String shipId
  );
}
