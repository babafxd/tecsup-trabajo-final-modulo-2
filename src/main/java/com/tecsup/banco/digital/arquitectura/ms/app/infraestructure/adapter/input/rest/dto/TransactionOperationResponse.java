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
public class TransactionOperationResponse {
    private String transaccionId;
    private String tipo;
    private String estado;
    private String descripcion;
}
