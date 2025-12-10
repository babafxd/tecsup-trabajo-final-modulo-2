package com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.persistence.entity;

import com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.persistence.generator.TransactionIdGenerator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaccion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {

    @Id
    @GeneratedValue(generator = "transaction-id-generator")
    @Column(name = "transaccion_id", length = 50)
    @GenericGenerator(
            name = "transaction-id-generator",
            type = TransactionIdGenerator.class
    )
    private String transaccionId;

    @ManyToOne
    @JoinColumn(name = "cuenta_origen_id", nullable = false)
    private AccountEntity cuentaOrigen;

    @ManyToOne
    @JoinColumn(name = "cuenta_destino_id", nullable = false)
    private AccountEntity cuentaDestino;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal monto;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal comision = new BigDecimal("5.00");

    @Column(nullable = false, length = 20)
    private String tipo = "TRANSFERENCIA";

    @Column(nullable = false, length = 20)
    private String estado = "PENDIENTE";

    @Column(length = 255)
    private String descripcion;

    @CreationTimestamp
    @Column(name = "fecha_creacion", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaCreacion;

}
