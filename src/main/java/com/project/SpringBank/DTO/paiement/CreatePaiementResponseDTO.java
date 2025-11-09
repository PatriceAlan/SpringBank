package com.project.SpringBank.DTO.paiement;

import com.project.SpringBank.entities.TypeTransaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePaiementResponseDTO {

    private Long idTransaction;
    private double montantPaiement;
    private TypeTransaction typeTransaction;
    private LocalDateTime datePaiement;
}
