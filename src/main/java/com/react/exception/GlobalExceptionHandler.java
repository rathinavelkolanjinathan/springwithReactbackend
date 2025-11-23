package com.react.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException
            (ResourceNotFoundException ex) {
        log.error("An error occurred: {}", ex);
        return new ResponseEntity<>(
                new ErrorResponse(ex.getClass(), ex.getMessage(),
                        HttpStatus.NOT_FOUND.value()),
                HttpStatus.NOT_FOUND
        );
    }
}
