package com.project.SpringBank.DTO.virement;

import com.project.SpringBank.entities.Compte;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateVirementDTO {

    private String ibanCompteEmetteur;
    private String ibanCompteBeneficiaire;
    private double montantVirement;
    private String libelleVirement;
    private LocalDateTime dateVirement;
}
