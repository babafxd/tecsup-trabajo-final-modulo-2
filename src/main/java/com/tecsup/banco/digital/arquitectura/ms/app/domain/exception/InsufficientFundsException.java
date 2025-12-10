package com.tecsup.banco.digital.arquitectura.ms.app.domain.exception;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
