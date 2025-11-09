package com.project.SpringBank.DTO.paiement;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePaiementRequestDTO {

    private double montantPaiement;
    private LocalDateTime datePaiement;
}
