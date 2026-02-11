package de.riversroses.infra.client;

import de.riversroses.planet.dto.RegisterShipResponseDto;
import de.riversroses.planet.dto.RegisterTeamRequestDto;
import de.riversroses.ship.dto.RegisterShipRequestDto;
import de.riversroses.ship.dto.ShipStatusDto;
import io.micronaut.retry.annotation.Fallback;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

@Fallback
@Slf4j
public class RegistrationClientFallback implements RegistrationClient {

    public RegisterShipResponseDto registerTeam(RegisterTeamRequestDto request) {
        log.warn("Fallback: Register Team failed.");
        return null;
    }

    public ShipStatusDto registerShip(String token, RegisterShipRequestDto request) {
        log.warn("Fallback: Register Ship failed.");
        return null;
    }

    public List<ShipStatusDto> myShips(String token) {
        log.warn("Fallback: Could not fetch ships.");
        return Collections.emptyList();
    }
}
