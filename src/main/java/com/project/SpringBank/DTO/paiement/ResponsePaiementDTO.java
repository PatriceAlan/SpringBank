package com.project.SpringBank.DTO.paiement;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePaiementDTO {

    private double montantPaiement;
    private LocalDateTime datePaiement;
    private Long idTransaction;
}
