package de.riversroses.missions.rest;

import de.riversroses.missions.business.MissionService;
import de.riversroses.missions.business.MissionValueGenerator;
import de.riversroses.missions.dto.MissionDto;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Singleton;
import jakarta.validation.Valid;

import java.util.Collection;

@Singleton
@Controller("/missions")
public class MissionController {
  
  private final MissionService missionService;
  private final MissionValueGenerator cursedDice;

  // nach DI
  public MissionController(MissionService missionService, MissionValueGenerator cursedDice) {
    this.missionService = missionService;
    this.cursedDice = cursedDice;
  }

  // vor DI
//   public MissionController() {
//     this.missionService = new MissionService();
//   }

  @Get
  public HttpResponse<Collection<MissionDto>> getMissions() {
    return HttpResponse.ok(missionService.getMissions());
  }

  @Get("/rng")
  public int getRng() {
    return cursedDice.getRandomNumber();
  }

  @Post("/complete")
  public HttpResponse<?> completeMission(@Body @Valid MissionDto mission) {
    missionService.removeMission(mission.id());
    return HttpResponse.ok();
  }

}
