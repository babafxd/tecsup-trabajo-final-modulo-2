package com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.persistence.mapper;

import com.tecsup.banco.digital.arquitectura.ms.app.domain.model.Account;
import com.tecsup.banco.digital.arquitectura.ms.app.domain.model.Transaction;
import com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.input.rest.dto.AccountSaldoResponse;
import com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.input.rest.dto.TransactionOperationResponse;
import com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.input.rest.dto.TransactionRequest;
import com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.input.rest.dto.TransactionResponse;
import com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.persistence.entity.TransactionEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AccountMapper.class})
public interface TransactionMapper {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    // Entity → Domain
    @Mapping(target = "cuentaOrigenId", source = "cuentaOrigen.cuentaId")
    @Mapping(target = "cuentaDestinoId", source = "cuentaDestino.cuentaId")
    @Mapping(target = "fechaCreacion", source = "fechaCreacion")
    Transaction toDomain(TransactionEntity entity);

    // Request → Domain
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "transaccionId", source = "transaccionId")
    @Mapping(target = "monto", source = "monto")
    @Mapping(target = "comision", source = "comision")
    @Mapping(target = "tipo", source = "tipo")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "descripcion", source = "descripcion")
    @Mapping(target = "cuentaOrigenId", source = "cuentaOrigenId")
    @Mapping(target = "cuentaDestinoId", source = "cuentaDestinoId")
    Transaction toDomain(TransactionRequest request);

    // Domain → Entity
    @Mapping(target = "fechaCreacion", ignore = true)
    TransactionEntity toEntity(Transaction domain);

    // Domain → Response
    @Mapping(target = "cuentaOrigenId", source = "cuentaOrigenId")
    @Mapping(target = "cuentaDestinoId", source = "cuentaDestinoId")
    TransactionResponse toResponse(Transaction domain);

    @Mapping(target = "transaccionId", source = "transaccionId")
    @Mapping(target = "tipo", source = "tipo")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "descripcion", source = "descripcion")
    TransactionOperationResponse toOperationResponse(Transaction domain);

    // Lists
    List<Transaction> toDomain(List<TransactionEntity> entities);
    List<TransactionResponse> toResponse(List<Transaction> domainList);
}
