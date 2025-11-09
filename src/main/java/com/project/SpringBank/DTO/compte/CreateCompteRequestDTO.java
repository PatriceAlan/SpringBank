package com.project.SpringBank.DTO.compte;

import com.project.SpringBank.entities.TypeCompte;
import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCompteRequestDTO {

    private String intituleCompte;
    private TypeCompte typeCompte;
    private Set<Long> titulairesCompte;
}
