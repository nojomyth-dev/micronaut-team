package de.riversroses.missions;

import de.riversroses.missions.db.InMemoryMissionRepository;
import de.riversroses.missions.dto.MissionDto;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class MissionControllerTest {


    @Singleton
    @Replaces(InMemoryMissionRepository.class)
    static class TestMissionRepository extends InMemoryMissionRepository {
    }

    @Inject
    @Client("/")
    HttpClient client;


    @Inject
    TestMissionRepository repo;

    @BeforeEach
    void setUp() {
        repo.getMissions().clear();
    }

    @Test
    void getMissions_returnsMissionsFromRepository() {
        MissionDto mission = new MissionDto(42L, "Test mission", "Seeded for controller test", 7);
        repo.addMission(42L, mission);

        var response = client.toBlocking().exchange(
                HttpRequest.GET("/missions"),
                Argument.listOf(MissionDto.class)
        );

        assertEquals(HttpStatus.OK, response.getStatus());
        List<MissionDto> missions = response.body();
        assertNotNull(missions);
        assertEquals(1, missions.size());
        assertTrue(missions.contains(mission), "Response should contain the seeded mission");
    }

    @Test
    void getMissions_returnsEmptyCollection_whenNoMissionsExist() {
        var response = client.toBlocking().exchange(
                HttpRequest.GET("/missions"),
                Argument.listOf(MissionDto.class)
        );

        assertEquals(HttpStatus.OK, response.getStatus());
        List<MissionDto> missions = response.body();
        assertNotNull(missions);
        assertTrue(missions.isEmpty());
    }


    @Test
    void completeMission_returns200_andRemovesMissionFromRepository() {
        MissionDto mission = new MissionDto(42L, "Test mission", "To be completed", 7);
        repo.addMission(42L, mission);
        assertTrue(repo.getMissions().containsKey(42L), "Precondition: mission must exist in repository");

        var response = client.toBlocking().exchange(
                HttpRequest.POST("/missions/complete", mission)
        );

        assertEquals(HttpStatus.OK, response.getStatus());
        assertFalse(repo.getMissions().containsKey(42L), "Mission should be removed after completing it");
    }
}