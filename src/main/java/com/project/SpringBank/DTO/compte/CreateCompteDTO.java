package com.project.SpringBank.DTO.compte;

import com.project.SpringBank.entities.TypeCompte;
import lombok.*;

import java.time.LocalDateTime;
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
    private Set<Long> titulaireCompte;
    private String intituleCompte;
    private LocalDateTime dateCreation;
}
