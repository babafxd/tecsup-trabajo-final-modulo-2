package com.tecsup.banco.digital.arquitectura.ms.app.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class Transaction {

    private String transaccionId;
    private String cuentaOrigenId;
    private String cuentaDestinoId;

    private BigDecimal monto;
    private BigDecimal comision;
    private String tipo;
    private String estado;

    private String descripcion;
    private LocalDateTime fechaCreacion;

    public boolean isTransferencia() {
        return "TRANSFERENCIA".equalsIgnoreCase(tipo);
    }

    public boolean isDeposito() {
        return "DEPOSITO".equalsIgnoreCase(tipo);
    }

    public boolean isRetiro() {
        return "RETIRO".equalsIgnoreCase(tipo);
    }

    public boolean isValidMonto() {
        return monto != null && monto.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isNew() {
        return transaccionId == null;
    }

    public boolean isValidComision() {
        return comision != null && monto.compareTo(BigDecimal.ZERO) >= 0;
    }

    @Override
    public String toString() {
        return String.format("Transaccion{id='%s', origen='%s', destino='%s', monto=%s}",
                transaccionId, cuentaOrigenId.toString(), cuentaDestinoId.toString(), monto);
    }
}
