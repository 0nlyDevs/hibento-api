package org.onlydevs.hibento.model.exception;

import static org.springframework.http.HttpStatus.FORBIDDEN;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = FORBIDDEN)
public class SessionNotLiveException extends RuntimeException {

  public SessionNotLiveException(String message) {
    super(message);
  }
}
