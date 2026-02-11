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


    /* Musterlösung A2

    @Get
    public MissionPayloadDto getMission() {
        var mission = missionService.generateOrReuseMission();
        return mission;
    }


    @Post("/complete")
    public HttpResponse<?> complete(@Body MissionCompletionDto completion) {
        // Security-Check: Störsignale aussortieren
        if (completion.getReward() <= 0) {
            // Antwortet mit HTTP 400, wenn die Belohnung ungültig ist
            return HttpResponse.badRequest(); 
        } else {
            // Nur wenn die Belohnung > 0 ist, wird die Mission markiert
            missionService.markCompleted(completion);
            return HttpResponse.ok();
        }
    }
    */
    

   //costom valiudationerrorhandeler
   //@Error(ConstraintExeption.class)
}
