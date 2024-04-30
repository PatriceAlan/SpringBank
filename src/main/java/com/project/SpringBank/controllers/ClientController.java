package com.project.SpringBank.controllers;

import com.project.SpringBank.DTO.client.CreateClientDTO;
import com.project.SpringBank.DTO.client.ResponseClientDTO;
import com.project.SpringBank.entities.Client;
import com.project.SpringBank.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;


    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ResponseClientDTO> createClient(@RequestBody CreateClientDTO clientDTO) {
        ResponseClientDTO createdClient = mapClientToResponseDTO(clientService.createClient(clientDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseClientDTO> updateClient(@PathVariable Long id, @RequestBody ResponseClientDTO clientDTO) {
        ResponseClientDTO updatedClient = mapClientToResponseDTO(clientService.updateClient(id, clientDTO));
        return ResponseEntity.ok(updatedClient);
    }

    @GetMapping
    public ResponseEntity<List<ResponseClientDTO>> listClients() {
        List<ResponseClientDTO> clients = clientService.listClients();
        return ResponseEntity.ok(clients);
    }


    public ResponseClientDTO mapClientToResponseDTO(Client client) {
        return ResponseClientDTO.builder()
                .idClient(client.getIdClient())
                .prenom(client.getPrenom())
                .nom(client.getNom())
                .dateNaissance(client.getDateNaissance())
                .email(client.getEmail())
                .numeroTelephone(client.getNumeroTelephone())
                .adressePostale(client.getAdressePostale())
                .dateCreation(client.getDateCreation())
                .build();
    }




}





