package com.project.SpringBank.DTO.compte;

import com.project.SpringBank.entities.TypeCompte;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCompteResponseDTO {

    private String intituleCompte;
    private TypeCompte typeCompte;
    private Set<Long> titulairesCompte;
    private String iban;
    private LocalDateTime dateCreation;

}
