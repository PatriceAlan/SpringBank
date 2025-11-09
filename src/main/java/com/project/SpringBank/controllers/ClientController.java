package com.project.SpringBank.controllers;

import com.project.SpringBank.DTO.HttpErrorResponse;
import com.project.SpringBank.DTO.client.*;
import com.project.SpringBank.entities.Client;
import com.project.SpringBank.mappers.ClientMapper;
import com.project.SpringBank.services.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @PostMapping
    public ResponseEntity<CreateClientResponseDTO> createClient(@RequestBody CreateClientRequestDTO clientDTO) {
        CreateClientResponseDTO createdClient = clientMapper.toDto(clientService.createClient(clientDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
    }

    @PutMapping(path = "/{idClient}")
    public ResponseEntity<UpdateClientResponseDTO> updateClient(
            @PathVariable Long idClient,
            @Valid @RequestBody UpdateClientRequestDTO updateClientRequestDTO) {

        Client clientMaj = clientService.updateClient(idClient, updateClientRequestDTO);
        UpdateClientResponseDTO updatedClient = clientMapper.toUpdateClientResponseDTO(clientMaj);
        return ResponseEntity.ok(updatedClient);
    }

    @GetMapping
    public ResponseEntity<?> listClients(
            @RequestParam String prenom,
            @RequestParam String nom) {

        if (prenom == null || prenom.isBlank() || nom == null || nom.isBlank()){
            HttpErrorResponse error = new HttpErrorResponse(
                    "Les paramètres 'prenom' et nom' sont obligatoires."
            );
            return ResponseEntity.badRequest().body(error);
        }
        List<GetClientResponseDTO> clients = clientService.listClients(prenom, nom);

        if (clients.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clients);
    }


}





