package com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.input.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {

    private String clienteId;
    //private String cuentaId;
    private String numeroCuenta;
    private BigDecimal saldo;
    private String estado;
}
