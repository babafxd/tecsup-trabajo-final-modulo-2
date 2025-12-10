package com.tecsup.banco.digital.arquitectura.ms.app.application.port.input;

import com.tecsup.banco.digital.arquitectura.ms.app.domain.model.Account;

public interface CreateAccountUseCase {
    Account execute(Account newAccount);
}
