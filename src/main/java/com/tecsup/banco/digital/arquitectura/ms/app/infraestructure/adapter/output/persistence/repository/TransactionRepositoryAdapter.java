package com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.persistence.repository;

import com.tecsup.banco.digital.arquitectura.ms.app.application.port.output.TransactionRepositoryPort;
import com.tecsup.banco.digital.arquitectura.ms.app.domain.model.Transaction;
import com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.persistence.entity.AccountEntity;
import com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.persistence.entity.ClientEntity;
import com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.persistence.entity.TransactionEntity;
import com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.persistence.mapper.ClientMapper;
import com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.persistence.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TransactionRepositoryAdapter implements TransactionRepositoryPort {

    private final TransactionJpaRepository transactionJpaRepository;
    private final AccountJpaRepository accountJpaRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public List<Transaction> findAll() {
        List<TransactionEntity> entities = this.transactionJpaRepository.findAll();
        return this.transactionMapper.toDomain(entities);
    }

    @Override
    public Transaction save(Transaction transaction) {


        TransactionEntity entity = this.transactionMapper.toEntity(transaction);

        AccountEntity accountOrigin = accountJpaRepository.findByCuentaId(transaction.getCuentaOrigenId())
                .orElseThrow(() -> new RuntimeException("Cuenta origen no encontrado"));

        AccountEntity accountDestiny = accountJpaRepository.findByCuentaId(transaction.getCuentaDestinoId())
                .orElseThrow(() -> new RuntimeException("Cuenta destino no encontrado"));

        entity.setCuentaOrigen(accountOrigin);
        entity.setCuentaDestino(accountDestiny);

        TransactionEntity savedEntity = this.transactionJpaRepository.save(entity);
        return this.transactionMapper.toDomain(savedEntity);



    }
}
