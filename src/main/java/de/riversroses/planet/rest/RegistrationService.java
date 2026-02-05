package de.riversroses.planet.rest;

import de.riversroses.planet.configuration.TeamConfiguration;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;

@Singleton
public class RegistrationService {
    private final RegistrationClient registrationClient;
    private final TeamConfiguration team;

    RegistrationService(RegistrationClient registrationClient, TeamConfiguration team) {
        this.registrationClient = registrationClient;
        this.team = team;
    }

    @EventListener
    public void register(StartupEvent event) {
        try {
            registrationClient.register(team);
            System.out.println("Team registered");
        } catch (Exception e) {
            System.out.println("no Server available" + e);
        }
    }
}
