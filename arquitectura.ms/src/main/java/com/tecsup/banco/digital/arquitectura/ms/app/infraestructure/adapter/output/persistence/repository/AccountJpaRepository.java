package com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.persistence.repository;

import com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.persistence.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

interface AccountJpaRepository extends JpaRepository<AccountEntity, String> {

    Optional<AccountEntity> findByNumeroCuenta(String numeroCuenta);

    Optional<AccountEntity> findByCuentaId(String cuentaId);

    boolean existsByNumeroCuenta(String numeroCuenta);

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM AccountEntity a WHERE a.cliente.id = :clienteId")
    boolean existsByCliente_Id(@Param("clienteId") String clienteId);

    @Query("SELECT a.saldo FROM AccountEntity a WHERE a.cuentaId = :cuentaId")
    Double findSaldoByCuentaId(@Param("cuentaId") String cuentaId);

    @Query("SELECT a FROM AccountEntity a WHERE a.cliente.id = :clientId")
    List<AccountEntity> findByClientId(@Param("clientId") String clientId);

    @Query("SELECT CASE WHEN a.saldo >= (:monto + :comision) THEN true ELSE false END FROM AccountEntity a WHERE a.cuentaId = :cuentaId")
    boolean hasSufficientSaldo(@Param("cuentaId") String cuentaId, @Param("monto") BigDecimal monto, @Param("comision") BigDecimal comision);

}
