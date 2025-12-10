package com.tecsup.banco.digital.arquitectura.ms.app.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class Account {

    private String cuentaId;
    //private Client cliente;
    private String clienteId;
    private String numeroCuenta;
    private BigDecimal saldo;
    private String estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    // ----------------------
    // REGLAS DE NEGOCIO
    // ----------------------
    public boolean isActive() {
        return "ACTIVO".equalsIgnoreCase(estado);
    }

    public boolean isClose() {
        return "CERRADO".equalsIgnoreCase(estado);
    }

    public boolean hasSaldoDisponible(BigDecimal monto) {
        return saldo != null &&
                monto != null &&
                saldo.compareTo(monto) >= 0;
    }

    public void depositar(BigDecimal monto) {
        if (monto.compareTo(BigDecimal.ZERO) > 0) {
            this.saldo = this.saldo.add(monto);
        }
    }

    public void retirar(BigDecimal monto) {
        if (hasSaldoDisponible(monto)) {
            this.saldo = this.saldo.subtract(monto);
        }
    }

    public boolean isNew() {
        return cuentaId == null;
    }

    public boolean validSaldo(BigDecimal saldo) {
        return saldo != null && saldo.compareTo(BigDecimal.ZERO) > 0;
    }

    @Override
    public String toString() {
        return String.format("Cuenta{id='%s', numero='%s', saldo=%s}",
                cuentaId, numeroCuenta, saldo);
    }
}
