package de.riversroses.missions.rest;

import de.riversroses.infra.logging.Logged;
import de.riversroses.missions.business.MissionService;
import de.riversroses.missions.dto.MissionCompletionDto;
import de.riversroses.missions.dto.MissionPayloadDto;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller("/missions") 
@Slf4j
public class MissionController {

    private final MissionService missionService;
        
    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    /*  Aufgabe 1
    @Get
    public MissionPayloadDto getMission() {
        var mission = missionService.generateOrReuseMission();
        return mission;
    }

    @Post("/complete")
    public HttpResponse<?> complete(@Body @Valid MissionCompletionDto completion) {

        missionService.markCompleted(completion);
        return HttpResponse.ok();
    }
    */

   //costom valiudationerrorhandeler
   //@Error(ConstraintExeption.class)
}
