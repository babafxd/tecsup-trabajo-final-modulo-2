package com.tecsup.banco.digital.arquitectura.ms.app.domain.exception;

public class InvalidTransactionDataException extends RuntimeException {
    public InvalidTransactionDataException(String message) {
        super(message);
    }
}

/*
    * monto > 0
    * cuenta_origen_id ≠ cuenta_destino_id
    * tipo valores: 'TRANSFERENCIA', 'DEPOSITO', 'RETIRO'
    * estado valores: 'PENDIENTE', 'COMPLETADA', 'FALLIDA', 'CANCELADA'
* */