package com.tecsup.banco.digital.arquitectura.ms.app.application.port.output;

import com.tecsup.banco.digital.arquitectura.ms.app.domain.model.Client;

import java.util.List;


public interface ClientRepositoryPort {

    Client save(Client client);

    boolean existsByEmail(String email);

    boolean existsByDocumento(String documento);

    List<Client> findAll();

    boolean existsById(String id);
}
