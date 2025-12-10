package com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.persistence.repository;

import com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.persistence.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

interface TransactionJpaRepository extends JpaRepository<TransactionEntity, String> {

    @Query("SELECT t FROM TransactionEntity t WHERE t.cuentaOrigen.cuentaId = :cuentaId ORDER BY t.fechaCreacion DESC")
    List<TransactionEntity> findByCuentaOrigen(@Param("cuentaId") String cuentaId);

    @Query("SELECT t FROM TransactionEntity t WHERE t.cuentaDestino.cuentaId = :cuentaId ORDER BY t.fechaCreacion DESC")
    List<TransactionEntity> findByCuentaDestino(@Param("cuentaId") String cuentaId);

    @Query("SELECT t FROM TransactionEntity t WHERE t.estado = :estado")
    List<TransactionEntity> findByEstado(@Param("estado") String estado);

}
