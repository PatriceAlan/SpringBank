package com.project.SpringBank.DTO.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetClientResponseDTO {

    private Long id;
    private String prenom;;
    private String nom;
    private LocalDate dateNaissance;
    private String email;
    private String numeroTelephone;
    private String adressePostale;
    private LocalDateTime dateCreation;
}
