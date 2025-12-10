package com.tecsup.banco.digital.arquitectura.ms.app.application.port.input;
import com.tecsup.banco.digital.arquitectura.ms.app.domain.model.Client;

import java.util.List;

public interface FindClientUseCase {
    List<Client> findAll();
}
