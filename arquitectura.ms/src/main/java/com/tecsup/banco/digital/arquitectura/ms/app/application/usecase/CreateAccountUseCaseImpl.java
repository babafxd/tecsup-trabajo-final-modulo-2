package com.tecsup.banco.digital.arquitectura.ms.app.application.usecase;

import com.tecsup.banco.digital.arquitectura.ms.app.application.port.input.CreateAccountUseCase;
import com.tecsup.banco.digital.arquitectura.ms.app.application.port.output.AccountRepositoryPort;
import com.tecsup.banco.digital.arquitectura.ms.app.application.port.output.ClientRepositoryPort;
import com.tecsup.banco.digital.arquitectura.ms.app.domain.exception.InvalidAccountDataException;
import com.tecsup.banco.digital.arquitectura.ms.app.domain.exception.InvalidClientDataException;
import com.tecsup.banco.digital.arquitectura.ms.app.domain.model.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Slf4j
public class CreateAccountUseCaseImpl implements CreateAccountUseCase {

    private final AccountRepositoryPort accountRepositoryPort;
    private final ClientRepositoryPort clientRepositoryPort;

    @Override
    public Account execute(Account newAccount) {

        if (newAccount == null) {
            throw new InvalidAccountDataException("* Cuenta no puede ser null");
        }

        if(!newAccount.validSaldo(newAccount.getSaldo())){
            throw new InvalidAccountDataException("* El saldo debe ser mayor a cero");
        }

        if(!newAccount.isActive())
        {
            throw new InvalidAccountDataException("* El estado debe ser ACTIVO");
        }

        if (!clientRepositoryPort.existsById(newAccount.getClienteId())) {
            throw new InvalidAccountDataException("* El cliente proporcionado no existe: " + newAccount.getClienteId());
        }

        if (accountRepositoryPort.existsByNumeroCuenta(newAccount.getNumeroCuenta())) {
            throw new InvalidAccountDataException("* El número de cuenta ya se encuentra registrado: " + newAccount.getNumeroCuenta());
        }

        return accountRepositoryPort.save(newAccount);

    }
}
