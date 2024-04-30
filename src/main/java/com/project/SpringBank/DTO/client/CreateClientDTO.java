package com.project.SpringBank.DTO.client;

import lombok.*;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateClientDTO {

    private String prenom;;
    private String nom;
    private LocalDate dateNaissance;
    private String email;
    private String numeroTelephone;
    private String adressePostale;
    private LocalDate dateCreation;


}
