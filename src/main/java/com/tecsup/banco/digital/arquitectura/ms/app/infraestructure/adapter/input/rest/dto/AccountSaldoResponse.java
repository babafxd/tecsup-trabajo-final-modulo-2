package com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.input.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountSaldoResponse {
    private BigDecimal saldo;
}
