package com.project.SpringBank.mappers;

import com.project.SpringBank.DTO.client.CreateClientResponseDTO;
import com.project.SpringBank.DTO.client.GetClientResponseDTO;
import com.project.SpringBank.DTO.client.UpdateClientRequestDTO;
import com.project.SpringBank.DTO.client.UpdateClientResponseDTO;
import com.project.SpringBank.entities.Client;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ClientMapper {

    public CreateClientResponseDTO toDto(Client client){

        return CreateClientResponseDTO.builder()
                .id(client.getIdClient())
                .prenom(client.getPrenom())
                .nom(client.getNom())
                .dateNaissance(client.getDateNaissance())
                .email(client.getEmail())
                .numeroTelephone(client.getNumeroTelephone())
                .adressePostale(client.getAdressePostale())
                .dateCreation(LocalDateTime.now())
                .build();
    }

    public UpdateClientResponseDTO toUpdateClientResponseDTO(Client client){
        return UpdateClientResponseDTO.builder()
                .id(client.getIdClient())
                .prenom(client.getPrenom())
                .nom(client.getNom())
                .dateNaissance(client.getDateNaissance())
                .email(client.getEmail())
                .numeroTelephone(client.getNumeroTelephone())
                .adressePostale(client.getAdressePostale())
                .dateCreation(LocalDateTime.now())
                .build();
    }

    public GetClientResponseDTO toGetClientResponseDTO(Client client){
        return GetClientResponseDTO.builder()
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
