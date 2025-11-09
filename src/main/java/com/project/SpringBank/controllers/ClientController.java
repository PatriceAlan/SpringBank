package com.project.SpringBank.controllers;

import com.project.SpringBank.DTO.client.CreateClientRequestDTO;
import com.project.SpringBank.DTO.client.CreateClientResponseDTO;
import com.project.SpringBank.DTO.client.UpdateClientRequestDTO;
import com.project.SpringBank.entities.Client;
import com.project.SpringBank.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<CreateClientResponseDTO> createClient(@RequestBody CreateClientRequestDTO clientDTO) {
        CreateClientResponseDTO createdClient = mapClientToCreateResponseDTO(clientService.createClient(clientDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateClientRequestDTO> updateClient(@PathVariable Long id, @RequestBody UpdateClientRequestDTO clientDTO) {
        Client clientMaj = clientService.updateClient(id, clientDTO);
        UpdateClientRequestDTO updatedClient = mapClientToUpdateRequestDTO(clientMaj);
        return ResponseEntity.ok(updatedClient);
    }

    @GetMapping
    public ResponseEntity<List<CreateClientResponseDTO>> listClients() {
        List<CreateClientResponseDTO> clients = clientService.listClients();
        return ResponseEntity.ok(clients);
    }


    public CreateClientResponseDTO mapClientToCreateResponseDTO(Client client) {
        return CreateClientResponseDTO.builder()
                .id(client.getIdClient())
                .prenom(client.getPrenom())
                .nom(client.getNom())
                .dateNaissance(client.getDateNaissance())
                .email(client.getEmail())
                .numeroTelephone(client.getNumeroTelephone())
                .adressePostale(client.getAdressePostale())
                .dateCreation(client.getDateCreation())
                .build();
    }

    public UpdateClientRequestDTO mapClientToUpdateRequestDTO(Client client) {
        return UpdateClientRequestDTO.builder()
                .prenom(client.getPrenom())
                .nom(client.getNom())
                .dateNaissance(client.getDateNaissance())
                .email(client.getEmail())
                .numeroTelephone(client.getNumeroTelephone())
                .adressePostale(client.getAdressePostale())
                .dateModification(LocalDateTime.now())
                .build();
    }


}





