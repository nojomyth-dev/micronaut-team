package de.riversroses.missions.rest;

import de.riversroses.infra.logging.Logged;
import de.riversroses.missions.business.MissionService;
import de.riversroses.missions.dto.MissionCompletionDto;
import de.riversroses.missions.dto.MissionPayloadDto;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.http.annotation.Error;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Controller("/missions")
@Slf4j
@Logged
public class MissionController {

    private final MissionService missionService;

    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

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

    @Error(exception = ConstraintViolationException.class)
    public HttpResponse<?> handleValidationErrors() {
        return HttpResponse.status(HttpStatus.I_AM_A_TEAPOT)
                .body("I'm a teapot. Stop sending junk missions!");
    }
}
