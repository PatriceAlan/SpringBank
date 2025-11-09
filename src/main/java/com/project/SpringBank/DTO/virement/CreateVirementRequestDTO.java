package com.project.SpringBank.DTO.virement;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateVirementRequestDTO {

    private String ibanCompteEmetteur;
    private String ibanCompteBeneficiaire;
    private double montantVirement;
    private String libelleVirement;
}
