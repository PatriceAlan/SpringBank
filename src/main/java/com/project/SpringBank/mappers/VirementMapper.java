package com.project.SpringBank.mappers;

import com.project.SpringBank.DTO.transaction.GetTransactionsCompteResponseDTO;
import com.project.SpringBank.DTO.virement.CreateVirementResponseDTO;
import com.project.SpringBank.entities.Transaction;
import com.project.SpringBank.entities.Virement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VirementMapper {

    private final TransactionMapper transactionMapper;

    public CreateVirementResponseDTO toVirementResponseDto(Virement virement) {
        GetTransactionsCompteResponseDTO transactionDTO = null;

        if (virement.getTransaction() != null) {
            transactionDTO = transactionMapper.toDto(virement.getTransaction());
        }

        return CreateVirementResponseDTO.builder()
                .idVirement(virement.getIdVirement())
                .dateVirement(virement.getDateVirement())
                .transaction(transactionDTO)
                .build();
    }
}
