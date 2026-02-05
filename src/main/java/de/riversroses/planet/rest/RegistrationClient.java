package de.riversroses.planet.rest;

import de.riversroses.planet.configuration.TeamConfiguration;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;

@Client("http://${server.ip}:8080")
public interface RegistrationClient {

    @Post("/register")
    void register(@Body TeamConfiguration team);
}
