package de.riversroses.infra.client;


import de.riversroses.planet.dto.RegisterShipResponseDto;
import de.riversroses.planet.dto.RegisterTeamRequestDto;
import de.riversroses.ship.dto.RegisterShipRequestDto;
import de.riversroses.ship.dto.ShipStatusDto;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.CircuitBreaker;

import java.util.List;

@Client(id = "game-server")
@CircuitBreaker(delay = "2s", attempts = "3", reset = "30s")
public interface RegistrationClient {

    @Post("/ships/teams/register")
    RegisterShipResponseDto registerTeam(@Body RegisterTeamRequestDto request);

    @Post("/ships/register")
    ShipStatusDto registerShip(@Header("X-Token") String token, @Body RegisterShipRequestDto request);

    @Get("/ships/me")
    List<ShipStatusDto> myShips(@Header("X-Token") String token);
}

