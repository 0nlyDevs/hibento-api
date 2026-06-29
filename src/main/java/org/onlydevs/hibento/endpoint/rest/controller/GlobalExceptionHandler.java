package org.onlydevs.hibento.endpoint.rest.controller;

import static org.springframework.http.HttpStatus.*;

import org.onlydevs.hibento.endpoint.rest.controller.dto.response.ErrorResponse;
import org.onlydevs.hibento.endpoint.rest.security.ForbiddenException;
import org.onlydevs.hibento.endpoint.rest.security.UnauthorizedException;
import org.onlydevs.hibento.model.exception.BadRequestException;
import org.onlydevs.hibento.model.exception.ConflictException;
import org.onlydevs.hibento.model.exception.InternalServerErrorException;
import org.onlydevs.hibento.model.exception.SessionNotLiveException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException ex) {
    var message = ex.getMessage() != null ? ex.getMessage() : "Resource not found";
    return ResponseEntity.status(NOT_FOUND).body(new ErrorResponse(message));
  }

  @ExceptionHandler(UnauthorizedException.class)
  public ResponseEntity<ErrorResponse> handleUnauthorized(UnauthorizedException ex) {
    var message = ex.getMessage() != null ? ex.getMessage() : "Unauthorized";
    return ResponseEntity.status(UNAUTHORIZED).body(new ErrorResponse(message));
  }

  @ExceptionHandler(ForbiddenException.class)
  public ResponseEntity<ErrorResponse> handleForbidden(ForbiddenException ex) {
    return ResponseEntity.status(FORBIDDEN).body(new ErrorResponse(ex.getMessage()));
  }

  @ExceptionHandler(SessionNotLiveException.class)
  public ResponseEntity<ErrorResponse> handleSessionNotLive(SessionNotLiveException ex) {
    return ResponseEntity.status(FORBIDDEN).body(new ErrorResponse(ex.getMessage()));
  }

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException ex) {
    return ResponseEntity.status(BAD_REQUEST).body(new ErrorResponse(ex.getMessage()));
  }

  @ExceptionHandler(ConflictException.class)
  public ResponseEntity<ErrorResponse> handleConflict(ConflictException ex) {
    return ResponseEntity.status(CONFLICT).body(new ErrorResponse(ex.getMessage()));
  }

  @ExceptionHandler(InternalServerErrorException.class)
  public ResponseEntity<ErrorResponse> handleInternalError(InternalServerErrorException ex) {
    return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ErrorResponse(ex.getMessage()));
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
    var message = "Invalid value for parameter '%s': '%s'".formatted(ex.getName(), ex.getValue());
    return ResponseEntity.status(BAD_REQUEST).body(new ErrorResponse(message));
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<ErrorResponse> handleMissingParam(
      MissingServletRequestParameterException ex) {
    var message = "Required parameter '%s' is missing".formatted(ex.getParameterName());
    return ResponseEntity.status(BAD_REQUEST).body(new ErrorResponse(message));
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorResponse> handleMessageNotReadable(
      HttpMessageNotReadableException ex) {
    return ResponseEntity.status(BAD_REQUEST).body(new ErrorResponse("Malformed request body"));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
    var message =
        ex.getBindingResult().getFieldErrors().stream()
            .map(e -> e.getField() + ": " + e.getDefaultMessage())
            .reduce((a, b) -> a + "; " + b)
            .orElse("Validation failed");
    return ResponseEntity.status(BAD_REQUEST).body(new ErrorResponse(message));
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ErrorResponse> handleAccessDenied(AccessDeniedException ex) {
    return ResponseEntity.status(FORBIDDEN)
        .body(new ErrorResponse(ex.getMessage() != null ? ex.getMessage() : "Access denied"));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
    return ResponseEntity.status(INTERNAL_SERVER_ERROR)
        .body(new ErrorResponse("An unexpected error occurred"));
  }
}
