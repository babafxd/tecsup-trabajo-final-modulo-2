package com.tecsup.banco.digital.arquitectura.ms.app.application.port.output;

import com.tecsup.banco.digital.arquitectura.ms.app.domain.model.Account;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AccountRepositoryPort {

    Account save(Account account);
    List<Account> findAll();
    boolean existsByClienteId(String clienteId);
    boolean existsByNumeroCuenta(String numeroCuenta);
    Optional<Account> findByNumeroCuenta(String numeroCuenta);
    Optional<Account> findByCuentaId(String cuentaId);
    boolean hasSufficientSaldo(String cuentaId, BigDecimal monto, BigDecimal comision);
}
