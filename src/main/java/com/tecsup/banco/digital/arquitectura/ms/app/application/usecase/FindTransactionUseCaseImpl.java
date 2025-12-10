package com.tecsup.banco.digital.arquitectura.ms.app.application.usecase;

import com.tecsup.banco.digital.arquitectura.ms.app.application.port.input.FindTransactionUseCase;
import com.tecsup.banco.digital.arquitectura.ms.app.application.port.output.TransactionRepositoryPort;
import com.tecsup.banco.digital.arquitectura.ms.app.domain.model.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Slf4j
public class FindTransactionUseCaseImpl implements FindTransactionUseCase {

    private final TransactionRepositoryPort transactionRepositoryPort;

    @Override
    public List<Transaction> findAll() {
        return transactionRepositoryPort.findAll();
    }
}
