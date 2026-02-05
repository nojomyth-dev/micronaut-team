package de.riversroses.planet.rest;

import de.riversroses.planet.TeamConfiguration;
import de.riversroses.planet.dto.PlanetDto;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/planet")
public class PlanetController {

    private final TeamConfiguration team;

    PlanetController(TeamConfiguration team) {
        this.team = team;
    }
  
  @Get
  public PlanetDto getPlanetInformation() {
      return new PlanetDto(team.name());
  }
}
