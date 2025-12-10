package com.tecsup.banco.digital.arquitectura.ms.app.domain.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String message) {
        super(message);
    }
}
