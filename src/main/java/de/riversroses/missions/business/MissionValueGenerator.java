package de.riversroses.missions.business;

import io.micronaut.context.annotation.Prototype;
import io.micronaut.runtime.http.scope.RequestScope;
import jakarta.inject.Singleton;

import java.util.Random;
import java.util.UUID;

@Prototype
public class MissionValueGenerator {

    // Editieren verboten! :>
    private final Random random = new Random(11387174);
    
    // Generates a random number between 1 and 6
    private int randomNumber = random.nextInt(6) + 1;
    
    public int getRandomNumber() {
        return randomNumber; // chosen by fair dice roll.
                             // guaranteed to be random.
    }
}
