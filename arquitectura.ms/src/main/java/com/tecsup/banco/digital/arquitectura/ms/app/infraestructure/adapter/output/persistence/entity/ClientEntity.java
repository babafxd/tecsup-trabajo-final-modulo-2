package com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.persistence.entity;

import com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.persistence.generator.ClientIdGenerator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IdGeneratorType;

import java.time.LocalDateTime;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {

    @Id
    @GeneratedValue(generator = "client-id-generator")
    @Column(length = 50)
    @GenericGenerator(
            name = "client-id-generator",
            type = ClientIdGenerator.class
    )
    private String id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, unique = true, length = 20)
    private String documento;

    @CreationTimestamp
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;
}
