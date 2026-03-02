package de.riversroses.missions.business;

import io.micronaut.context.annotation.Prototype;
import jakarta.inject.Singleton;

import java.util.Random;

@Singleton
public class MissionRng {
    private final Random random = new Random();

    public double coordinate(double worldSize) {
        return random.nextDouble() * worldSize;
    }
}
