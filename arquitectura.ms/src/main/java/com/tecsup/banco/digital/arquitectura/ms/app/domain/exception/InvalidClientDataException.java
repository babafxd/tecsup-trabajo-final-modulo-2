package com.tecsup.banco.digital.arquitectura.ms.app.domain.exception;

public class InvalidClientDataException extends RuntimeException {
    public InvalidClientDataException(String message) {
        super(message);
    }
}

/*
* Email formato válido
* Documento único en el sistema
* */