package de.riversroses.strategy;

import de.riversroses.config.StrategyConfig;
import de.riversroses.infra.client.GameServerClient;
import de.riversroses.planet.business.RegistrationService;
import de.riversroses.scan.dto.RadarScanResponseDto;
import de.riversroses.ship.dto.SetCourseRequestDto;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Singleton
@Slf4j
public class PatrolStrategyService {

  private final RegistrationService registrationService;
  private final StrategyConfig config;
  private final Random random = new Random();
  private final GameServerClient gameServerClient;

  public PatrolStrategyService(GameServerClient gameServerClient,
                               RegistrationService registrationService,
                               StrategyConfig config) {

    this.registrationService = registrationService;
    this.config = config;
    this.gameServerClient = gameServerClient;
  }

  @Scheduled(fixedDelay = "5s")
  public void tick() {

    String token = registrationService.token();
    String shipId = registrationService.primaryShipId();

    if (token == null || shipId == null) {
      return;
    }

    try {
      RadarScanResponseDto scan = gameServerClient.scan(token, shipId);

      SetCourseRequestDto course = calculateCourse(scan, shipId);

      gameServerClient.setCourse(token, course);
    } catch (Exception e) {
      log.warn("Strategy tick failed: {}", e.toString());
    }
  }

  private SetCourseRequestDto calculateCourse(RadarScanResponseDto scan, String shipId) {
    log.info("Scan: {} resources", scan.resources().size());

    double targetX;
    double targetY;

    if (!scan.resources().isEmpty() && random.nextDouble() > config.homeBiasOrDefault()) {
      var res = scan.resources().get(0);
      targetX = res.x();
      targetY = res.y();
      log.info("Heading to resource {} at {},{}", res.oreId(), targetX, targetY);
    } else {
      // random patrol point near center
      targetX = 500 + (random.nextDouble() - 0.5) * 300;
      targetY = 500 + (random.nextDouble() - 0.5) * 300;
      log.info("Patrolling to {},{}", targetX, targetY);
    }

    return new SetCourseRequestDto(shipId, targetX, targetY);
  }
}
