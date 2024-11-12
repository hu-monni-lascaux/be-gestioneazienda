package com.example.gestioneazienda.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> usernameNotFound(Exception exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorMessage(new Date(), exception.getMessage(), request.getDescription(false)), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> genericRuntime(Exception exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorMessage(new Date(), exception.getMessage(), request.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> generic(Exception exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorMessage(new Date(), exception.getMessage(), request.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<?> recordNotFound(Exception exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorMessage(new Date(), exception.getMessage(), request.getDescription(false)), HttpStatus.NOT_FOUND);
    }
}
