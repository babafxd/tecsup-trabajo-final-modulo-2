package com.tecsup.banco.digital.arquitectura.ms.app.application.usecase;

import com.tecsup.banco.digital.arquitectura.ms.app.application.port.input.FindAccountUseCase;
import com.tecsup.banco.digital.arquitectura.ms.app.application.port.output.AccountRepositoryPort;
import com.tecsup.banco.digital.arquitectura.ms.app.domain.exception.AccountNotFoundException;
import com.tecsup.banco.digital.arquitectura.ms.app.domain.model.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Slf4j
public class FindAccountUseCaseImpl implements FindAccountUseCase {

    private final AccountRepositoryPort accountRepositoryPort;

    @Override
    @Transactional(readOnly = true)
    public List<Account> findAll() {
        return accountRepositoryPort.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Account findByNumeroCuenta(String numeroCuenta) {
        if (numeroCuenta == null) {
            throw new IllegalArgumentException("Invalid Numero de cuenta");
        }

        return accountRepositoryPort.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new AccountNotFoundException(numeroCuenta));
    }

}
