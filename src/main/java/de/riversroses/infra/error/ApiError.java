package de.riversroses.infra.error;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.HttpStatus;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
@Introspected
public record ApiError(
    HttpStatus code,
    String message) {
}
