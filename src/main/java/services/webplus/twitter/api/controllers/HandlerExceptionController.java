package services.webplus.twitter.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import services.webplus.twitter.api.utils.errors.StandardError;
import services.webplus.twitter.api.utils.exceptions.DuplicationObjectException;

@RestControllerAdvice
public class HandlerExceptionController {
    
    @ExceptionHandler({DuplicationObjectException.class})
    public ResponseEntity<?> customBadRequestException(Exception exception) {
        var error = StandardError.builder()
            .message(exception.getMessage())
            .status(HttpStatus.BAD_REQUEST.value())
            .timestamp(System.currentTimeMillis())
            .build();

        return ResponseEntity.badRequest().body(error);
    }
}
