package com.tecsup.banco.digital.arquitectura.ms.app.domain.exception;

public class InvalidAccountDataException extends RuntimeException {
    public InvalidAccountDataException(String message) {
        super(message);
    }
}
/*
    * saldo >= 0
    * numero_cuenta único
    * estado valores permitidos: 'ACTIVO', 'CERRADO'
* */