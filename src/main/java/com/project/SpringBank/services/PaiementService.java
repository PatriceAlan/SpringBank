package com.project.SpringBank.services;

import com.project.SpringBank.DTO.paiement.ResponsePaiementDTO;
import com.project.SpringBank.entities.Paiement;
import com.project.SpringBank.entities.Transaction;
import com.project.SpringBank.entities.TypeTransaction;
import com.project.SpringBank.entities.TypeSource;
import com.project.SpringBank.repositories.PaiementRepository;
import jakarta.transaction.Transactional;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Builder
public class PaiementService {

    private final PaiementRepository paiementRepository;

    @Transactional
    public Paiement createPaiement(ResponsePaiementDTO paiementDTO){

        Transaction transaction = new Transaction();
        transaction.setTypeTransaction(TypeTransaction.DEBIT);
        transaction.setTypeSource(TypeSource.CARTE);
        transaction.setDateTransaction(LocalDateTime.now());
        transaction.setMontantTransaction(paiementDTO.getMontantPaiement());

        Paiement paiement = Paiement.builder()
                .idTransaction(transaction.getIdTransaction())
                .montantPaiement(paiementDTO.getMontantPaiement())
                .datePaiement(paiementDTO.getDatePaiement())
                .build();

        return paiementRepository.save(paiement);
    }


    public ResponsePaiementDTO mapPaiementToResponseDTO(Paiement paiement) {
        return ResponsePaiementDTO.builder()
                .montantPaiement(paiement.getMontantPaiement())
                .datePaiement(paiement.getDatePaiement())
                .build();
    }
}
