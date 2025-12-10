package com.tecsup.banco.digital.arquitectura.ms.app.application.port.input;

import com.tecsup.banco.digital.arquitectura.ms.app.domain.model.Transaction;

import java.util.List;

public interface FindTransactionUseCase {
    List<Transaction> findAll();
}
