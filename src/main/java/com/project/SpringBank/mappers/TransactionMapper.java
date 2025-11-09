package com.project.SpringBank.mappers;

import com.project.SpringBank.DTO.transaction.GetTransactionsCompteResponseDTO;
import com.project.SpringBank.entities.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public GetTransactionsCompteResponseDTO toDto(Transaction transaction) {
        Long idSource = null;

        switch (transaction.getTypeSource()) {
            case CARTE -> {
                if (transaction.getPaiementCarte() != null) {
                    idSource = transaction.getPaiementCarte().getIdPaiement();
                }
            }
            case VIREMENT -> {
                if (transaction.getVirement() != null) {
                    idSource = transaction.getVirement().getIdVirement();
                }
            }
            default -> idSource = null;
        }

        return GetTransactionsCompteResponseDTO.builder()
                .idTransaction(transaction.getIdTransaction())
                .montantTransaction(transaction.getMontantTransaction())
                .typeTransaction(transaction.getTypeTransaction())
                .typeSource(transaction.getTypeSource())
                .idSource(idSource)
                .dateTransaction(transaction.getDateTransaction())
                .build();
    }
}

