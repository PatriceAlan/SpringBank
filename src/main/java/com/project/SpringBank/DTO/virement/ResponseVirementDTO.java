package com.project.SpringBank.DTO.virement;
import com.project.SpringBank.entities.Transaction;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVirementDTO {

    private Long idVirement;
    private LocalDateTime dateVirement;
    private List<Transaction> transaction;

}
