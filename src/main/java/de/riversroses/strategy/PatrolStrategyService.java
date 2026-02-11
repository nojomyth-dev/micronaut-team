package de.riversroses.strategy;

import de.riversroses.config.StrategyConfig;
import de.riversroses.infra.client.GameServerClient;
import de.riversroses.planet.business.RegistrationService;
import de.riversroses.scan.dto.RadarScanResponseDto;
import de.riversroses.ship.dto.SetCourseRequestDto;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import io.micronaut.scheduling.annotation.Scheduled;

import java.util.Random;

@Singleton
@Slf4j
public class PatrolStrategyService {

  private final GameServerClient gameClient;
  private final RegistrationService registrationService;
  private final StrategyConfig config;
  private final Random random = new Random();

  public PatrolStrategyService(GameServerClient gameClient,
      RegistrationService registrationService,
      StrategyConfig config) {

    this.gameClient = gameClient;
    this.registrationService = registrationService;
    this.config = config;
  }

  @Scheduled(fixedDelay = "5s")
  public void tick() {
    
    String token = registrationService.token();
    String shipId = registrationService.primaryShipId();

    if (token == null || shipId == null) {
      return;
    }
    // Das sollen die geneerieren 
    //Sie sollen Scan aufruFen
    //Sie sollen setcourse aufrufen
    try {
      RadarScanResponseDto scan = gameClient.scan(token, shipId);
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

      gameClient.setCourse(token, new SetCourseRequestDto(shipId, targetX, targetY));
    } catch (Exception e) {
      log.warn("Strategy tick failed: {}", e.toString());
    }
  }
   
}
