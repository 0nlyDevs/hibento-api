package org.onlydevs.hibento.model.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = INTERNAL_SERVER_ERROR)
public class InternalServerErrorException extends RuntimeException {

  public InternalServerErrorException(String message) {
    super(message);
  }
}
