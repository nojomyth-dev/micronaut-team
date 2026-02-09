package de.riversroses.missions.model;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name = "mission_log")
@Serdeable
@Data
public class MissionLog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String missionId;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private double x;

  @Column(nullable = false)
  private double y;

  @Column(nullable = true)
  private Integer reward;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private MissionStatus status;

  @Column(nullable = false)
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
