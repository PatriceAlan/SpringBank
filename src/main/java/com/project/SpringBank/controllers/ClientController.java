package com.project.SpringBank.controllers;

import com.project.SpringBank.entities.Client;
import com.project.SpringBank.services.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/ajouter-client")
    public ResponseEntity<Client> ajouterOuMettreAJourClient(@RequestBody Client client) {
        try {
            Client savedClient = clientService.sauvegarderOuMettreAJourClient(client);
            return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> rechercherClientParId(@PathVariable Long id) {
        try {
            Optional<Client> client = clientService.rechercherClient(id);
            return client.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/tous-les-clients")
    public ResponseEntity<List<Client>> afficherTousLesClients() {
        List<Client> clients = clientService.afficherTousLesClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }
}
