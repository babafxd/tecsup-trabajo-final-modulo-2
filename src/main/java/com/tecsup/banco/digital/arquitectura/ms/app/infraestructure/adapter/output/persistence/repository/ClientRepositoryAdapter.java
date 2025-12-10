package com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.persistence.repository;

import com.tecsup.banco.digital.arquitectura.ms.app.application.port.output.ClientRepositoryPort;
import com.tecsup.banco.digital.arquitectura.ms.app.domain.model.Client;
import com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.persistence.entity.ClientEntity;
import com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.persistence.mapper.ClientMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ClientRepositoryAdapter implements ClientRepositoryPort {

    private final ClientJpaRepository clientJpaRepository;
    private final ClientMapper clientMapper;

    @Override
    public Client save(Client client) {
        ClientEntity entity = this.clientMapper.toEntity(client);
        ClientEntity savedEntity = this.clientJpaRepository.save(entity);
        return this.clientMapper.toDomain(savedEntity);
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.clientJpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByDocumento(String documento) {
        return this.clientJpaRepository.existsByDocumento(documento);
    }

    @Override
    public List<Client> findAll() {
        List<ClientEntity> entities = this.clientJpaRepository.findAll();
        return this.clientMapper.toDomain(entities);
    }

    @Override
    public boolean existsById(String id) {
        return this.clientJpaRepository.existsById(id);
    }

}
