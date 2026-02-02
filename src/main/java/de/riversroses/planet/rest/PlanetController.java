package de.riversroses.planet.rest;

import de.riversroses.planet.dto.PlanetDto;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Controller("/planet")
@AllArgsConstructor
@Data
@Slf4j
public class PlanetController {
  
  @Get
  public PlanetDto getPlanetInformation() {

    return new PlanetDto("Our team");
  }
}
