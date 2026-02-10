package de.riversroses.missions.business;

import java.util.Random;

public class MissionRng {
    private final Random random = new Random(11387174);

    public double coordinate(double worldSize) {
        return random.nextDouble() * worldSize;
    }
}
