package de.riversroses.missions.rest;

import de.riversroses.missions.business.MissionService;
import de.riversroses.missions.dto.MissionCompletionDto;
import de.riversroses.missions.dto.MissionPayloadDto;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

@Controller("/missions")
public class MissionController {

    private final MissionService missionService;

    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    @Get
    public MissionPayloadDto getMission() {
        return missionService.generateOrReuseMission();
    }

    @Post("/complete")
    public HttpResponse<?> completeMission(@Body MissionCompletionDto mission) {
        if (mission.reward <= 0) {
            return HttpResponse.badRequest();
        }
        missionService.markCompleted(mission);
        return HttpResponse.ok();
    }

}
