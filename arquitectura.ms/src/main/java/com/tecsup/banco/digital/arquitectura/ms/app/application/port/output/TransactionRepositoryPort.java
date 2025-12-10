package com.tecsup.banco.digital.arquitectura.ms.app.application.port.output;
import com.tecsup.banco.digital.arquitectura.ms.app.domain.model.Client;
import com.tecsup.banco.digital.arquitectura.ms.app.domain.model.Transaction;

import java.util.List;

public interface TransactionRepositoryPort {

    List<Transaction> findAll();
    Transaction save(Transaction transaction);
}
