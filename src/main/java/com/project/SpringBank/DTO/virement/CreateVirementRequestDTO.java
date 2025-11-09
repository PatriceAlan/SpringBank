package com.project.SpringBank.DTO.virement;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateVirementRequestDTO {

    private String ibanCompteEmetteur;
    private String ibanCompteReceveur;
    private double montantVirement;
    private String libelleVirement;
}
