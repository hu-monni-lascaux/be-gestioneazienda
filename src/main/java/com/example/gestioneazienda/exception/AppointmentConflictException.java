package com.example.gestioneazienda.exception;

public class AppointmentConflictException extends RuntimeException{

    public AppointmentConflictException(String message) {
        super(message);
    }
}
