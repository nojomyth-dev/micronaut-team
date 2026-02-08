package de.riversroses.infra.error;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;
import jakarta.validation.ConstraintViolationException;

@Singleton
public class GlobalErrorHandler implements ExceptionHandler<Exception, HttpResponse<ApiError>> {

  @Override
  public HttpResponse<ApiError> handle(HttpRequest request, Exception exception) {
    if (exception instanceof ConstraintViolationException cve) {
      return HttpResponse.badRequest(
          new ApiError(HttpStatus.BAD_REQUEST, cve.getMessage()));
    }
    if (exception instanceof IllegalArgumentException iae) {
      return HttpResponse.badRequest(
          new ApiError(HttpStatus.BAD_REQUEST, iae.getMessage()));
    }
    exception.printStackTrace();
    return HttpResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error: " + exception.getMessage()));
  }
}
