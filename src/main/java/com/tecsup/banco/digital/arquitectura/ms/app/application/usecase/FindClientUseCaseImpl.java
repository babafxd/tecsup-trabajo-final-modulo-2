package com.tecsup.banco.digital.arquitectura.ms.app.application.usecase;

import com.tecsup.banco.digital.arquitectura.ms.app.application.port.input.FindClientUseCase;
import com.tecsup.banco.digital.arquitectura.ms.app.application.port.output.ClientRepositoryPort;
import com.tecsup.banco.digital.arquitectura.ms.app.domain.model.Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Slf4j
public class FindClientUseCaseImpl implements FindClientUseCase {

    private final ClientRepositoryPort clientRepositoryPort;

    @Override
    public List<Client> findAll() {
        return clientRepositoryPort.findAll();
    }
}
