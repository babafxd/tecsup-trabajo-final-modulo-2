package com.tecsup.banco.digital.arquitectura.ms.app.application.port.input;

import com.tecsup.banco.digital.arquitectura.ms.app.domain.model.Transaction;

public interface CreateTransactionUseCase {
    Transaction execute(Transaction newTransaction);
}
