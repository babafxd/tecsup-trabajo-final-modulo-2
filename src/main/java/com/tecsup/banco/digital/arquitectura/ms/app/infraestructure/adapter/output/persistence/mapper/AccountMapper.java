package com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.persistence.mapper;

import com.tecsup.banco.digital.arquitectura.ms.app.domain.model.Account;
import com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.input.rest.dto.AccountRequest;
import com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.input.rest.dto.AccountResponse;
import com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.input.rest.dto.AccountSaldoResponse;
import com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.persistence.entity.AccountEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ClientMapper.class})
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    // Entity → Domain
    @Mapping(target = "clienteId", source = "cliente.id")
    Account toDomain(AccountEntity entity);

    // Request → Domain
    @BeanMapping(ignoreByDefault = true)
    //@Mapping(target = "cuentaId", source = "cuentaId")
    @Mapping(target = "numeroCuenta", source = "numeroCuenta")
    @Mapping(target = "saldo", source = "saldo")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "clienteId", source = "clienteId")
    Account toDomain(AccountRequest request);

    // Domain → Entity
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    AccountEntity toEntity(Account domain);

    // Domain → Response
    AccountResponse toResponse(Account domain);

    @Mapping(target = "saldo", source = "saldo")
    AccountSaldoResponse toSaldoResponse(Account domain);

    // Lists
    List<Account> toDomain(List<AccountEntity> entities);
    List<AccountResponse> toResponse(List<Account> domainList);
}
