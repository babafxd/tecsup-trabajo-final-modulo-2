package com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.input.rest.controller;

import com.tecsup.banco.digital.arquitectura.ms.app.application.port.input.CreateAccountUseCase;
import com.tecsup.banco.digital.arquitectura.ms.app.application.port.input.FindAccountUseCase;
import com.tecsup.banco.digital.arquitectura.ms.app.domain.model.Account;
import com.tecsup.banco.digital.arquitectura.ms.app.domain.model.Client;
import com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.input.rest.dto.*;
import com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.persistence.mapper.AccountMapper;
import com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.persistence.mapper.ClientMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class AccountController {
    private final FindAccountUseCase findAccountUseCase;
    private final CreateAccountUseCase  createAccountUseCase;
    private final AccountMapper mapper;

    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAllAccounts() {
        List<Account> accounts = this.findAccountUseCase.findAll();
        List<AccountResponse> responses = this.mapper.toResponse(accounts);
        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody AccountRequest request) {

        Account newAccount = this.mapper.toDomain(request);
        Account createAccount = this.createAccountUseCase.execute(newAccount);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.mapper.toResponse(createAccount));
    }

    @GetMapping("/search")
    public ResponseEntity<AccountSaldoResponse> getAccountByNumeroCuenta(@RequestParam String numeroCuenta) {
        Account account = this.findAccountUseCase.findByNumeroCuenta(numeroCuenta);
        AccountSaldoResponse response = this.mapper.toSaldoResponse(account);
        return ResponseEntity.ok(response);
    }

}
