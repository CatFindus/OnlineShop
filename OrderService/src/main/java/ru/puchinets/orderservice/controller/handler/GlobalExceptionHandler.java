package ru.puchinets.orderservice.controller.handler;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import ru.puchinets.orderservice.exceptions.QuantityException;
import ru.puchinets.orderservice.model.errors.ErrorDetails;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        log.warn("Validation error with message {}", ex.getMessage());
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(QuantityException.class)
    public ResponseEntity<?> handleConstraintViolationException(QuantityException ex, WebRequest request) {
        log.warn("Cant change state item of order because: '{}'", ex.getMessage());
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.valueOf(422), ex.getMessage(),  request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.valueOf(422));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex, WebRequest request) {
        log.error("Unhandled error with message: {} {}", ex.getMessage(), ex.getClass().getSimpleName());
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
