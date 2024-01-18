package com.project.SpringBank.services.client;

import com.project.SpringBank.entities.Client;
import com.project.SpringBank.repositories.ClientRepository;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Builder
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

public Client sauvegarderOuMettreAJour(Client client) {

        return this.clientRepository.save(Client.builder()
                .idClient(client.getIdClient())
                .prenom(client.getPrenom())
                .nom(client.getNom())
                .dateNaissance(client.getDateNaissance())
                .email(client.getEmail())
                .numeroTelephone(client.getNumeroTelephone())
                .adressePostale(client.getAdressePostale())
                .dateCreation(client.getDateCreation())
                .build());
    }

    public Optional<Client> findById(Long id) {

        return this.clientRepository.findById(id);
    }
}
