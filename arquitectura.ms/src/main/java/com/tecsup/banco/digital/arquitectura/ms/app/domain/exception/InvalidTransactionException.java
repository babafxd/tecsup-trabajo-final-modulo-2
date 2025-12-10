package com.tecsup.banco.digital.arquitectura.ms.app.domain.exception;

public class InvalidTransactionException extends RuntimeException {
    public InvalidTransactionException(String message) {
        super(message);
    }
}
