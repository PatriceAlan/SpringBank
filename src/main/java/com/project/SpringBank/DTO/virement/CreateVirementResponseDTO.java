package com.project.SpringBank.DTO.virement;
import com.project.SpringBank.DTO.transaction.GetTransactionsCompteResponseDTO;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateVirementResponseDTO {

    private Long idVirement;
    private LocalDateTime dateVirement;
    private double montantVirement;
    private GetTransactionsCompteResponseDTO transaction;

}
