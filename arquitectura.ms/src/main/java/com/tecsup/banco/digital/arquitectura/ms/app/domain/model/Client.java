package com.tecsup.banco.digital.arquitectura.ms.app.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class Client {

    private String id;  // CLI001
    private String nombre;
    private String email;
    private String documento;
    private LocalDateTime fechaCreacion;

    // ----------------------
    // REGLAS DE NEGOCIO
    // ----------------------
    public boolean hasValidEmail() {
        return email != null &&
                email.contains("@") &&
                email.contains(".") &&
                email.length() >= 5;
    }

    public boolean hasValidDocumento() {
        return documento != null && !documento.trim().isEmpty();
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public String toString() {
        return String.format("Cliente{id='%s', nombre='%s', email='%s'}",
                id, nombre, email);
    }
}