package com.project.SpringBank.DTO.transaction;

import com.project.SpringBank.entities.TypeSource;
import com.project.SpringBank.entities.TypeTransaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GetTransactionsCompteResponseDTO {

    private Long idTransaction;
    private double montantTransaction;
    private TypeTransaction typeTransaction;
    private TypeSource typeSource;
    private Long idSource;
    private LocalDateTime dateTransaction;
}
