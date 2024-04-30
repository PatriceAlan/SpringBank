package com.project.SpringBank.DTO.client;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseClientDTO {

    private Long idClient;
    private String prenom;;
    private String nom;
    private LocalDate dateNaissance;
    private String email;
    private String numeroTelephone;
    private String adressePostale;
    private LocalDate dateCreation;

}
