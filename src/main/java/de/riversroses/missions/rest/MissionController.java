package de.riversroses.missions.rest;

import java.util.Collection;

import de.riversroses.missions.business.MissionValueGenerator;
import de.riversroses.missions.business.MissionService;
import de.riversroses.missions.dto.MissionDto;
import io.micronaut.context.annotation.Prototype;
import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.runtime.http.scope.RequestScope;
import jakarta.inject.Singleton;

// Zum austricksen
@Singleton
@Controller("/missions")
public class MissionController {
  
  private final MissionService missionService;
  private final MissionValueGenerator cursedDice;

  // DependencyInjection sollen die später machen
  public MissionController(MissionService missionService, MissionValueGenerator cursedDice) {
    this.missionService = missionService;
    this.cursedDice = cursedDice;
  }

  // Das ist am Anfang da und wird dann umgeschrieben auf Dependency Injection
  // public MissionController() {
  //   InMemoryMissionRepository missionRepository = new InMemoryMissionRepository();
  //   this.missionService = new MissionService(missionRepository);
  // }

  @Get
  public HttpResponse<Collection<MissionDto>> getMissions() {
    return HttpResponse.ok(missionService.getMissions());
  }

  @Get("/rng")
  public int getRng() {
    return cursedDice.getRandomNumber();
  }

  // body, reward granted
  @Post("/{id}")
  public void postMissionCompletion(Long id) {
    missionService.removeMission(id);
  }
}
