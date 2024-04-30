package com.project.SpringBank.DTO.virement;

import com.project.SpringBank.entities.Compte;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateVirementDTO {

    private Compte ibanCompteEmetteur;
    private Compte CompteBeneficiaire;
    private double montant;
    private String libelleVirement;
    private LocalDate dateVirement;
}
