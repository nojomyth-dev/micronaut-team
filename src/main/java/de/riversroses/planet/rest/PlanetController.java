package de.riversroses.planet.rest;

import de.riversroses.config.TeamConfig;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.serde.annotation.Serdeable;

@Controller("/planet")
public class PlanetController {

  private final TeamConfig teamConfig;

  public PlanetController(TeamConfig teamConfig) {
    this.teamConfig = teamConfig;
  }

  @Get
  public PlanetInfo planet() {
    return new PlanetInfo(teamConfig.name(), teamConfig.planetName());
  }

  @Serdeable
  public record PlanetInfo(String teamName, String planetName) {
  }
}
