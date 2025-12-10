package com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.persistence.mapper;

import com.tecsup.banco.digital.arquitectura.ms.app.domain.model.Client;
import com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.input.rest.dto.ClientRequest;
import com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.input.rest.dto.ClientResponse;
import com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.persistence.entity.ClientEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    // Entity → Domain
    Client toDomain(ClientEntity entity);

    // Request → Domain (solo campos que el usuario puede enviar)
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "documento", source = "documento")
    Client toDomain(ClientRequest request);

    // Domain → Entity
    @Mapping(target = "fechaCreacion", ignore = true)
    ClientEntity toEntity(Client domain);

    // Domain → Response
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "documento", source = "documento")
    ClientResponse toResponse(Client domain);

    // List mappings
    List<Client> toDomain(List<ClientEntity> entityList);

    List<ClientResponse> toResponse(List<Client> domainList);
}
