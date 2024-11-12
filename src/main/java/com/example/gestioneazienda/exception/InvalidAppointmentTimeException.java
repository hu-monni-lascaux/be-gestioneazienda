package com.example.gestioneazienda.exception;

public class InvalidAppointmentTimeException extends RuntimeException{
    private final String message;

    public InvalidAppointmentTimeException(String message) {
        this.message = message;
    }

    public InvalidAppointmentTimeException(String message, String message1) {
        super(message);
        this.message = message1;
    }
}
