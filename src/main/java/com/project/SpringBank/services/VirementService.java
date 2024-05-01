package com.project.SpringBank.services;

import com.project.SpringBank.DTO.virement.CreateVirementDTO;
import com.project.SpringBank.DTO.virement.ResponseVirementDTO;
import com.project.SpringBank.entities.Transaction;
import com.project.SpringBank.entities.TypeSource;
import com.project.SpringBank.entities.TypeTransaction;
import com.project.SpringBank.entities.Virement;
import com.project.SpringBank.repositories.VirementRepository;
import jakarta.transaction.Transactional;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Builder
public class VirementService {

    private final VirementRepository virementRepository;

    @Transactional
    public ResponseVirementDTO createVirement(CreateVirementDTO virementDTO) {

        // Création de la transaction
        Transaction transaction = new Transaction();
        transaction.setTypeTransaction(TypeTransaction.DEBIT);
        transaction.setTypeSource(TypeSource.VIREMENT);
        transaction.setDateTransaction(LocalDateTime.now());
        transaction.setMontantTransaction(virementDTO.getMontantVirement());

        // Création du virement avec association de la transaction
        Virement virement = Virement.builder()
                .ibanCompteBeneficiaire(virementDTO.getIbanCompteBeneficiaire())
                .ibanCompteEmetteur(virementDTO.getIbanCompteEmetteur())
                .montantVirement(virementDTO.getMontantVirement())
                .dateVirement(LocalDateTime.now())
                .libelleVirement(virementDTO.getLibelleVirement())
                .transaction(transaction)
                .build();


        Virement savedVirement = this.virementRepository.save(virement);

        return mapVirementToResponseDTO(savedVirement);
    }

    public ResponseVirementDTO mapVirementToResponseDTO(Virement virement) {
        return ResponseVirementDTO.builder()
                .idVirement(virement.getIdVirement())
                .dateVirement(virement.getDateVirement())
                .transaction(List.of(virement.getTransaction()))
                .build();
    }

    public List<ResponseVirementDTO> listerVirements() {
        return this.virementRepository.findAll().stream()
                .map(this::mapVirementToResponseDTO)
                .toList();
    }

}
