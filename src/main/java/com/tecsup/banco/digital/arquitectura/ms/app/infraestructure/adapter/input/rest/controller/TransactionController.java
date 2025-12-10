package com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.input.rest.controller;

import com.tecsup.banco.digital.arquitectura.ms.app.application.port.input.CreateTransactionUseCase;
import com.tecsup.banco.digital.arquitectura.ms.app.application.port.input.FindTransactionUseCase;
import com.tecsup.banco.digital.arquitectura.ms.app.domain.model.Account;
import com.tecsup.banco.digital.arquitectura.ms.app.domain.model.Client;
import com.tecsup.banco.digital.arquitectura.ms.app.domain.model.Transaction;
import com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.input.rest.dto.*;
import com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.persistence.mapper.TransactionMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class TransactionController {

    private final FindTransactionUseCase findTransactionUseCase;
    private final CreateTransactionUseCase createTransactionUseCase;
    private final TransactionMapper mapper;

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getAllTransactions() {
        List<Transaction> transactions = this.findTransactionUseCase.findAll();
        List<TransactionResponse> responses = this.mapper.toResponse(transactions);
        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<TransactionOperationResponse> createTransaction(@Valid @RequestBody TransactionRequest request) {

        Transaction newTransaction = this.mapper.toDomain(request);
        Transaction createTransaction = this.createTransactionUseCase.execute(newTransaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.mapper.toOperationResponse(createTransaction));
    }

}
