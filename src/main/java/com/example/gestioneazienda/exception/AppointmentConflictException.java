package com.example.gestioneazienda.exception;

public class AppointmentConflictException extends RuntimeException{
    private final String message;

    public AppointmentConflictException(String message) {
        this.message = message;
    }

    public AppointmentConflictException(String message, String message1) {
        super(message);
        this.message = message1;
    }
}
