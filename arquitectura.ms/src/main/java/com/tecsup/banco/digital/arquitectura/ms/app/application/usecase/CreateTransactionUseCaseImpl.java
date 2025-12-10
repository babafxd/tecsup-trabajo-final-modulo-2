package com.tecsup.banco.digital.arquitectura.ms.app.application.usecase;

import com.tecsup.banco.digital.arquitectura.ms.app.application.port.input.CreateTransactionUseCase;
import com.tecsup.banco.digital.arquitectura.ms.app.application.port.output.AccountRepositoryPort;
import com.tecsup.banco.digital.arquitectura.ms.app.application.port.output.NotificationPort;
import com.tecsup.banco.digital.arquitectura.ms.app.application.port.output.TransactionRepositoryPort;
import com.tecsup.banco.digital.arquitectura.ms.app.domain.exception.*;
import com.tecsup.banco.digital.arquitectura.ms.app.domain.model.Transaction;
import com.tecsup.banco.digital.arquitectura.ms.app.domain.model.TransactionStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Transactional(noRollbackFor = {
        AccountNotFoundException.class,
        InsufficientFundsException.class
})
@Slf4j
public class CreateTransactionUseCaseImpl implements CreateTransactionUseCase {

    private final TransactionRepositoryPort transactionRepositoryPort;
    private final AccountRepositoryPort accountRepositoryPort;
    private final NotificationPort notificationPort;

    @Override
    public Transaction execute(Transaction newTransaction) {

        String messageError = "";

        if (newTransaction == null) {
            throw new InvalidTransactionDataException("* Transaction no puede ser null");
        }

        newTransaction.setEstado(TransactionStatus.PENDIENTE.name());

        try {

            if (!newTransaction.isValidMonto()) {
                return cancelTrasanction(newTransaction, "El monto debe ser mayor a cero");
            }

            if (!newTransaction.isValidComision()) {
                return cancelTrasanction(newTransaction, "La comisión debe ser igual o mayor a cero");
            }

            if (newTransaction.getCuentaOrigenId().equalsIgnoreCase(newTransaction.getCuentaDestinoId())) {
                throw new InvalidTransactionDataException("Las cuentas bancarias deben ser distintas");
            }

            if (!(newTransaction.isDeposito() || newTransaction.isRetiro() || newTransaction.isTransferencia())) {
                return cancelTrasanction(newTransaction, "Tipo de transacciones permitidas: DEPOSITO, TRANSFERENCIA, RETIRO");
            }

            if (accountRepositoryPort.findByCuentaId(newTransaction.getCuentaOrigenId()).isEmpty()) {
                messageError = "La cuenta origen no existe: " + newTransaction.getCuentaOrigenId();
                return cancelTrasanction(newTransaction, messageError);
            }

            if (accountRepositoryPort.findByCuentaId(newTransaction.getCuentaDestinoId()).isEmpty()) {
                messageError = "La cuenta destino no existe: " + newTransaction.getCuentaDestinoId();
                return cancelTrasanction(newTransaction, messageError);
            }

            if (!accountRepositoryPort.hasSufficientSaldo(newTransaction.getCuentaOrigenId(), newTransaction.getMonto(), newTransaction.getComision())) {
                messageError = "Saldo insuficiente en cuenta origen";
                return cancelTrasanction(newTransaction, messageError);
            }


            newTransaction.setEstado(TransactionStatus.COMPLETADA.name());
            Transaction transactionCompleted = transactionRepositoryPort.save(newTransaction);
            notificationPort.notify("Transferencia realizada con éxito: " + transactionCompleted.getTransaccionId(), transactionCompleted.getCuentaOrigenId());
            return transactionCompleted;


        } catch (InvalidTransactionDataException e) {
            throw e;

        }catch (Exception e) {
            messageError = "* Ocurrió un error al realizar la transacción";
            newTransaction.setEstado(TransactionStatus.FALLIDA.name());
            newTransaction.setDescripcion(messageError);
            transactionRepositoryPort.save(newTransaction);
            notificationPort.notify("La transferencia falló: " + e.getMessage(), newTransaction.getCuentaOrigenId());

            throw new InvalidTransactionException(messageError);
        }
    }

    private Transaction cancelTrasanction(Transaction tx, String descripcion) {
        tx.setEstado(TransactionStatus.CANCELADA.name());
        tx.setDescripcion(descripcion);
        tx.setFechaCreacion(LocalDateTime.now());
        Transaction transactionCanceled = transactionRepositoryPort.save(tx);
        notificationPort.notify("La transferencia fue cancelada: " + descripcion, transactionCanceled.getCuentaOrigenId());
        return transactionCanceled;
    }
}
