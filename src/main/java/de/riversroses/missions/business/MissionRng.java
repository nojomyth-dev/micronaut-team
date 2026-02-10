package de.riversroses.missions.business;

import io.micronaut.context.annotation.Prototype;

import java.util.Random;

@Prototype
public class MissionRng {
    private final Random random = new Random(11387174);

    public double coordinate(double worldSize) {
        return random.nextDouble() * worldSize;
    }
}
