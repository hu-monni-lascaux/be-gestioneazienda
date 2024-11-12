package com.example.gestioneazienda.exception;

public class RecordNotFoundException extends RuntimeException {
    private final String message;

    public RecordNotFoundException(String message) {
        this.message = message;
    }

    public RecordNotFoundException(String message, String msg) {
        super(message);
        this.message = msg;
    }
}
