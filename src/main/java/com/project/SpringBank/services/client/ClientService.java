package com.project.SpringBank.services.client;

import com.project.SpringBank.entities.Client;
import com.project.SpringBank.repositories.ClientRepository;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Builder
public class ClientService {

    private final ClientRepository clientRepository;

public Client sauvegarderOuMettreAJourClient(Client client) {
    if (client == null) {
        throw new IllegalArgumentException("Le client ne peut pas être nul");
    }
    if (client.getIdClient() != null) {

        return this.clientRepository.saveAndFlush(client);
    } else {

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
}

    public Optional<Client> findById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("L'ID ne peut pas être nul");
        }

        return this.clientRepository.findById(id);
    }

    public List<Client> findAll() {
        return this.clientRepository.findAll();
    }
}
