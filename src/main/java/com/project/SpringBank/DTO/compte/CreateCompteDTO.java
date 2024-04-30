package com.project.SpringBank.DTO.compte;

import com.project.SpringBank.entities.Client;
import com.project.SpringBank.entities.TypeCompte;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCompteDTO {

    private String iban;
    private Long numeroCompte;
    private double solde;
    private int cleRIB;
    private TypeCompte typeCompte;
    private Set<Client> titulaireCompte;
    private String intituleCompte;
    private LocalDate dateCreation;
}
