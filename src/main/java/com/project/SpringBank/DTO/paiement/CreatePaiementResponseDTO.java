package com.project.SpringBank.DTO.paiement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaiementResponseDTO {

    private Long idTransaction;
    private double typeTransaction;
    private LocalDateTime dateCreation;
}
