package com.project.SpringBank.services;

import com.project.SpringBank.DTO.client.CreateClientRequestDTO;
import com.project.SpringBank.DTO.client.CreateClientResponseDTO;
import com.project.SpringBank.DTO.client.UpdateClientRequestDTO;
import com.project.SpringBank.entities.Client;
import com.project.SpringBank.repositories.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Builder
public class ClientService {

    private final ClientRepository clientRepository;

    @Transactional
    public Client createClient(CreateClientRequestDTO clientDTO) {
        Client client = Client.builder()
                .prenom(clientDTO.getPrenom())
                .nom(clientDTO.getNom())
                .dateNaissance(clientDTO.getDateNaissance())
                .email(clientDTO.getEmail())
                .numeroTelephone(clientDTO.getNumeroTelephone())
                .adressePostale(clientDTO.getAdressePostale())
                .dateCreation(LocalDateTime.now())
                .build();

        return clientRepository.save(client);

    }

    @Transactional
    public Client updateClient(Long id, UpdateClientRequestDTO clientDTO) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isEmpty()) {
            throw new IllegalArgumentException("Le client avec l'ID " + id + " est introuvable.");
        }

        Client client = clientOptional.get();
        client.setPrenom(clientDTO.getPrenom());
        client.setNom(clientDTO.getNom());
        client.setDateNaissance(clientDTO.getDateNaissance());
        client.setEmail(clientDTO.getEmail());
        client.setNumeroTelephone(clientDTO.getNumeroTelephone());
        client.setAdressePostale(clientDTO.getAdressePostale());
        client.setDateModification(LocalDateTime.now());

        return clientRepository.save(client);
    }

    public List<CreateClientResponseDTO> listClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .map(this::mapClientToResponseDTO)
                .toList();
    }

    public CreateClientResponseDTO mapClientToResponseDTO(Client client) {
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

}
