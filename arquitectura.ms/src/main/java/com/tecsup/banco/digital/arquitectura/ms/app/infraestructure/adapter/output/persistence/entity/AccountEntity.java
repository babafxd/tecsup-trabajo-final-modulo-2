package com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.persistence.entity;

import com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.persistence.generator.AccountIdGenerator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cuenta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {

    @Id
    @GeneratedValue(generator = "account-id-generator")
    @Column(name = "cuenta_id", length = 50)
    @GenericGenerator(
            name = "account-id-generator",
            type = AccountIdGenerator.class
    )
    private String cuentaId;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClientEntity cliente;

    @Column(name = "numero_cuenta", nullable = false, unique = true, length = 20)
    private String numeroCuenta;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal saldo = BigDecimal.ZERO;

    @Column(nullable = false, length = 20)
    private String estado = "ACTIVO";

    @CreationTimestamp
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
}
