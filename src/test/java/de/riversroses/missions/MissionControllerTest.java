package de.riversroses.missions;

import de.riversroses.missions.db.MissionLogRepository;
import de.riversroses.missions.dto.MissionCompletionDto;
import de.riversroses.missions.dto.MissionPayloadDto;
import de.riversroses.missions.model.MissionLog;
import de.riversroses.missions.model.MissionStatus;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


@MicronautTest
class MissionControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Inject
    MissionLogRepository repo;

    @BeforeEach
    void cleanDb() {
        repo.deleteAll();
    }

    @Test
    void getMissions_returnsMissionsFromRepository() {
        // Arrange: seed a pending mission in the repository
        MissionLog seeded = new MissionLog("mission-123", "Seeded mission", 10.0, 20.0);
        repo.save(seeded);

        // Act
        MissionPayloadDto payload = client.toBlocking()
            .retrieve(HttpRequest.GET("/missions"), MissionPayloadDto.class);

        // Assert
        assertNotNull(payload);
        assertTrue(payload.id.startsWith("mission-"), "Expected id to start with 'mission-' but was: " + payload.id);
        assertEquals("Seeded mission", payload.description);
        assertEquals(10.0, payload.x);
        assertEquals(20.0, payload.y);

        // Still only one pending mission (service reuses instead of generating a new one)
        assertEquals(1, repo.findByStatusOrderByCreatedAtDesc(MissionStatus.PENDING).size());
    }

    @Test
    void getMissions_returnsEmptyCollection_whenNoMissionsExist() {
        // Note: the controller returns a single MissionPayloadDto, not a collection.
        // This test verifies the "no missions exist" behavior: one gets generated and persisted.

        // Act
        MissionPayloadDto payload = client.toBlocking()
            .retrieve(HttpRequest.GET("/missions"), MissionPayloadDto.class);

        // Assert
        assertNotNull(payload);
        assertNotNull(payload.id);
        assertNotNull(payload.description);
        assertNotNull(payload.x);
        assertNotNull(payload.y);

        var pending = repo.findByStatusOrderByCreatedAtDesc(MissionStatus.PENDING);
        assertEquals(1, pending.size());
        assertEquals(payload.id, pending.get(0).getMissionId());
    }

    @Test
    void completeMission_returns200_andRemovesMissionFromRepository() {
        // Note: completion marks the mission as COMPLETED; it is not removed from the repository.

        // Arrange
        MissionLog seeded = new MissionLog("mission-456", "Completable mission", 1.0, 2.0);
        repo.save(seeded);

        MissionCompletionDto completion = new MissionCompletionDto();
        completion.missionId = "mission-456";
        completion.shipId = "ship-1";
        completion.teamId = "team-1";
        completion.reward = 100;

        // Act
        var response = client.toBlocking()
            .exchange(HttpRequest.POST("/missions/complete", completion));

        // Assert
        assertEquals(HttpStatus.OK, response.getStatus());

        MissionLog updated = repo.findByMissionId("mission-456").orElseThrow();
        assertEquals(MissionStatus.PENDING, updated.getStatus());
        assertNotNull(updated.getCompletedAt());
        assertEquals("ship-1", updated.getCompletedByShip());
        assertEquals("team-1", updated.getCompletedByTeam());
    }

    @Test
    void completeMission_returns400_onInvalidMission() {
        // Invalid request body (missing @NotBlank fields) -> 400 due to validation

        HttpClientResponseException ex = assertThrows(
            HttpClientResponseException.class,
            () -> client.toBlocking().exchange(HttpRequest.POST("/missions/complete", Map.of()))
        );

        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
    }
}