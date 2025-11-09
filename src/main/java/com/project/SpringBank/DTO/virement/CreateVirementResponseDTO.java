package com.project.SpringBank.DTO.virement;
import com.project.SpringBank.DTO.transaction.GetTransactionsCompteResponseDTO;
import com.project.SpringBank.entities.Transaction;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateVirementResponseDTO {

    private Long idVirement;
    private LocalDateTime dateVirement;
    private GetTransactionsCompteResponseDTO transaction;

}
