package com.project.SpringBank.mappers;

import com.project.SpringBank.DTO.paiement.CreatePaiementResponseDTO;
import com.project.SpringBank.entities.PaiementCarte;
import org.springframework.stereotype.Component;

@Component
public class PaiementMapper {

    public CreatePaiementResponseDTO toCreatePaiementResponseDTO(PaiementCarte paiementCarte){
        return CreatePaiementResponseDTO.builder()
                .idTransaction(paiementCarte.getTransaction().getIdTransaction())
                .montantPaiement(paiementCarte.getMontantPaiementCarte())
                .typeTransaction(paiementCarte.getTransaction().getTypeTransaction())
                .datePaiement(paiementCarte.getDatePaiement())
                .build();
    }

}
