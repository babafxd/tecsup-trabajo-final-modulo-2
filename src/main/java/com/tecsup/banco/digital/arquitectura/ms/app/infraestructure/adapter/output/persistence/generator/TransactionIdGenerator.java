package com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.persistence.generator;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;

import java.util.EnumSet;

public class TransactionIdGenerator implements BeforeExecutionGenerator {
    @Override
    public Object generate(
            SharedSessionContractImplementor session,
            Object owner,
            Object currentValue,
            EventType eventType
    ) {

        String sql = "SELECT MAX(transaccion_id ) FROM transaccion ";

        String lastId = (String) session
                .createNativeQuery(sql, String.class)
                .getSingleResultOrNull();

        int nextNumber = (lastId == null)
                ? 1
                : Integer.parseInt(lastId.replace("TXN", "")) + 1;

        return "TXN" + String.format("%03d", nextNumber);

    }

    @Override
    public EnumSet<EventType> getEventTypes() {
        return EnumSet.of(EventType.INSERT);
    }
}
