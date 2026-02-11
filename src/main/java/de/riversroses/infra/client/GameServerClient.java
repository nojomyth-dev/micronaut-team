package de.riversroses.infra.client;

import de.riversroses.scan.dto.RadarScanResponseDto;
import de.riversroses.ship.dto.SetCourseRequestDto;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;

@Client(id = "game-server")
public interface GameServerClient {

  @Post("/ships/course")
  void setCourse(@Header("X-Token") String token, @Body SetCourseRequestDto req);

  @Get("/scan{?shipId}")
  RadarScanResponseDto scan(
          @Header("X-Token") String token,
          @Nullable @QueryValue("shipId") String shipId
  );
}
