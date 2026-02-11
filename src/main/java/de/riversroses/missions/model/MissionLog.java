package de.riversroses.missions.model;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

import java.time.Instant;

@Serdeable
@Data
public class MissionLog {

  private Long id;

  private String missionId;

  private String description;

  private double x;

  private double y;

  private Integer reward;

  private MissionStatus status;

  private Instant createdAt;

  private Instant completedAt;

  private String completedByShip;
  private String completedByTeam;

  protected MissionLog() {
  }

  public MissionLog(String missionId, String description, double x, double y) {
    this.missionId = missionId;
    this.description = description;
    this.x = x;
    this.y = y;
    this.reward = 100;
    this.status = MissionStatus.PENDING;
    this.createdAt = Instant.now();
  }

  public void complete(String shipId, String teamId) {
    this.status = MissionStatus.COMPLETED;
    this.completedAt = Instant.now();
    this.completedByShip = shipId;
    this.completedByTeam = teamId;
  }
}
