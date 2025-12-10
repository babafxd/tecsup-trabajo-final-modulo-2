package com.tecsup.banco.digital.arquitectura.ms.app.application.usecase;

import com.tecsup.banco.digital.arquitectura.ms.app.application.port.input.CreateClientUseCase;
import com.tecsup.banco.digital.arquitectura.ms.app.application.port.output.ClientRepositoryPort;
import com.tecsup.banco.digital.arquitectura.ms.app.domain.exception.InvalidClientDataException;
import com.tecsup.banco.digital.arquitectura.ms.app.domain.model.Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Slf4j
public class CreateClientUseCaseImpl implements CreateClientUseCase {
    private final ClientRepositoryPort clientRepositoryPort;

    @Override
    public Client execute(Client newClient) {

        if (newClient == null) {
            throw new InvalidClientDataException("* Cliente no puede ser null");
        }

        if (!newClient.hasValidEmail()) {
            throw new InvalidClientDataException("* Cliente no tiene un email válido");
        }

        if (!newClient.hasValidDocumento()) {
            throw new InvalidClientDataException("* Cliente no tiene un documento válido");
        }

        if (clientRepositoryPort.existsByDocumento(newClient.getDocumento())) {
            throw new InvalidClientDataException("* El documento brindado ya se encuentra registrado: " + newClient.getDocumento());
        }


        return clientRepositoryPort.save(newClient);

    }
}
