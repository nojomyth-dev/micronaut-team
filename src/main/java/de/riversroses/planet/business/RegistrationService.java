package de.riversroses.planet.business;

import de.riversroses.config.TeamConfig;
import de.riversroses.infra.client.GameServerClient;
import de.riversroses.planet.dto.RegisterTeamRequestDto;
import de.riversroses.ship.dto.RegisterShipRequestDto;
import de.riversroses.ship.dto.ShipStatusDto;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.runtime.event.ApplicationStartupEvent;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

@Singleton
public class RegistrationService implements ApplicationEventListener<ApplicationStartupEvent> {

  private static final Logger LOG = LoggerFactory.getLogger(RegistrationService.class);

  private final GameServerClient gameClient;
  private final TeamConfig teamConfig;

  private volatile double homeX;
  private volatile double homeY;

  // state
  private volatile String teamId;
  private volatile String primaryShipId;

  public RegistrationService(GameServerClient gameClient, TeamConfig teamConfig) {
    this.gameClient = gameClient;
    this.teamConfig = teamConfig;
  }

  @Override
  public void onApplicationEvent(ApplicationStartupEvent event) {
    try {
      registerTeamIfNeeded();
      registerShipIfNeeded();
    } catch (Exception e) {
      LOG.error("Failed to register with game server: {}", e.getMessage());
    }
  }

  private void registerTeamIfNeeded() {
    List<ShipStatusDto> ships = getMyShipsSafe();
    if (!ships.isEmpty()) {
      teamId = ships.get(0).teamId();
      LOG.info("Team already registered as {} ({} ships)", teamId, ships.size());
      return;
    }

    LOG.info("No ships found (or token unknown). Attempting to register team...");
    RegisterTeamRequestDto req = new RegisterTeamRequestDto(
        teamConfig.token(), teamConfig.name(), teamConfig.planetName());
    var resp = gameClient.registerTeam(req);
    teamId = resp.teamId();

    this.homeX = resp.planetX();
    this.homeY = resp.planetY();

    LOG.info("Registered team {} with id {}", teamConfig.name(), teamId);
  }

  private void registerShipIfNeeded() {
    List<ShipStatusDto> ships = getMyShipsSafe();
    if (!ships.isEmpty()) {
      primaryShipId = ships.get(0).shipId();
      LOG.info("Using existing ship {}", primaryShipId);
      return;
    }
    ShipStatusDto ship = gameClient.registerShip(
        teamConfig.token(), new RegisterShipRequestDto("Flagship"));
    primaryShipId = ship.shipId();
    LOG.info("Registered new ship {}", primaryShipId);
  }

  /**
   * Helper to safely fetch ships.
   * Catches 404/401/Unknown Token errors and treats them as "empty list"
   * so registration can proceed.
   */
  private List<ShipStatusDto> getMyShipsSafe() {
    try {
      List<ShipStatusDto> ships = gameClient.myShips(teamConfig.token());
      return ships != null ? ships : Collections.emptyList();
    } catch (HttpClientResponseException e) {
      // If the token is unknown to the server, we assume the team is not registered
      // yet.
      if (e.getStatus() == HttpStatus.UNAUTHORIZED ||
          e.getMessage().contains("Unknown Token")) {
        return Collections.emptyList();
      }
      throw e;
    }
  }

  public String token() {
    return teamConfig.token();
  }

  public String teamId() {
    return teamId;
  }

  public String primaryShipId() {
    return primaryShipId;
  }

  public double getHomeX() { return homeX; }
  public double getHomeY() { return homeY; }
}
