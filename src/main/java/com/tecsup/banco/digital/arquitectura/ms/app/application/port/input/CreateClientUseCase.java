package com.tecsup.banco.digital.arquitectura.ms.app.application.port.input;

import com.tecsup.banco.digital.arquitectura.ms.app.domain.model.Client;

public interface CreateClientUseCase {
    Client execute(Client newClient);
}
