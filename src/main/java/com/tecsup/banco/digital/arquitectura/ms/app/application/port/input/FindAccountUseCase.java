package com.tecsup.banco.digital.arquitectura.ms.app.application.port.input;

import com.tecsup.banco.digital.arquitectura.ms.app.domain.model.Account;

import java.util.List;

public interface FindAccountUseCase {
    List<Account> findAll();
    Account findByNumeroCuenta(String numeroCuenta);
}
