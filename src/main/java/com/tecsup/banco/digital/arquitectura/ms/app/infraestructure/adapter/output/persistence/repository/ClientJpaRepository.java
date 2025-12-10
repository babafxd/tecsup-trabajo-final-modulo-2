package com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.persistence.repository;

import com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.persistence.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface ClientJpaRepository extends JpaRepository<ClientEntity, String> {

    Optional<ClientEntity> findByEmail(String email);
    Optional<ClientEntity> findById(String id);
    boolean existsByEmail(String email);

    boolean existsByDocumento(String documento);

    @Query("SELECT c FROM ClientEntity c WHERE LOWER(c.nombre) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<ClientEntity> findByNombreContainingIgnoreCase(@Param("name") String name);
}
