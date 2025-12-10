package com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.input.rest.controller;

import com.tecsup.banco.digital.arquitectura.ms.app.application.port.input.CreateClientUseCase;
import com.tecsup.banco.digital.arquitectura.ms.app.application.port.input.FindClientUseCase;
import com.tecsup.banco.digital.arquitectura.ms.app.domain.exception.InvalidClientDataException;
import com.tecsup.banco.digital.arquitectura.ms.app.domain.model.Client;
import com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.input.rest.dto.ClientRequest;
import com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.input.rest.dto.ClientResponse;
import com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.adapter.output.persistence.mapper.ClientMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/client")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class ClientController {

    private final CreateClientUseCase createClientUseCase;
    private final FindClientUseCase findClientUseCase;
    private final ClientMapper mapper;

    @GetMapping
    public ResponseEntity<List<ClientResponse>> getAllClients() {
        List<Client> clients = this.findClientUseCase.findAll();
        List<ClientResponse> responses = this.mapper.toResponse(clients);
        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<ClientResponse> createClient(@Valid @RequestBody ClientRequest request) {

        Client newClient = this.mapper.toDomain(request);
        Client createClient = this.createClientUseCase.execute(newClient);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.mapper.toResponse(createClient));

    }

}
