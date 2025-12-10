package com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.persistence.repository;

import com.tecsup.banco.digital.arquitectura.ms.app.application.port.output.AccountRepositoryPort;
import com.tecsup.banco.digital.arquitectura.ms.app.domain.model.Account;
import com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.persistence.entity.AccountEntity;
import com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.persistence.entity.ClientEntity;
import com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.persistence.mapper.AccountMapper;
import com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.persistence.mapper.ClientMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class AccountRepositoryAdapter implements AccountRepositoryPort {

    private final AccountJpaRepository accountJpaRepository;
    private final ClientJpaRepository clientJpaRepository;
    private final AccountMapper accountMapper;

    @Override
    public Account save(Account account) {
        AccountEntity entity = this.accountMapper.toEntity(account);

        ClientEntity client = clientJpaRepository.findById(account.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        entity.setCliente(client);

        AccountEntity savedEntity = this.accountJpaRepository.save(entity);
        return this.accountMapper.toDomain(savedEntity);
    }

    @Override
    public List<Account> findAll() {
        List<AccountEntity> entities = this.accountJpaRepository.findAll();
        return this.accountMapper.toDomain(entities);
    }

    @Override
    public boolean existsByClienteId(String clienteId) {
        boolean existe = this.accountJpaRepository.existsByCliente_Id(clienteId);
        return existe;
    }

    @Override
    public boolean existsByNumeroCuenta(String numeroCuenta) {
        return this.accountJpaRepository.existsByNumeroCuenta(numeroCuenta);
    }

    @Override
    public Optional<Account> findByNumeroCuenta(String numeroCuenta) {
        return this.accountJpaRepository.findByNumeroCuenta(numeroCuenta).map(this.accountMapper::toDomain);
    }

    @Override
    public Optional<Account> findByCuentaId(String cuentaId) {
        return this.accountJpaRepository.findByCuentaId(cuentaId).map(this.accountMapper::toDomain);
    }

    @Override
    public boolean hasSufficientSaldo(String cuentaId, BigDecimal monto, BigDecimal comision) {
        return accountJpaRepository.hasSufficientSaldo(cuentaId, monto,comision);
    }
}
