package com.project.SpringBank.services;

import com.project.SpringBank.DTO.virement.CreateVirementDTO;
import com.project.SpringBank.DTO.virement.ResponseVirementDTO;
import com.project.SpringBank.entities.Transaction;
import com.project.SpringBank.entities.TypeSource;
import com.project.SpringBank.entities.TypeTransaction;
import com.project.SpringBank.entities.Virement;
import com.project.SpringBank.repositories.CompteRepository;
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

    private final CompteRepository compteRepository;

    @Transactional
    public ResponseVirementDTO createVirement(CreateVirementDTO dto) {

        var compteEmet = compteRepository.findByIban(dto.getIbanCompteEmetteur())
                .orElseThrow(()-> new IllegalArgumentException("Compte emetteur introuvable"));
        var compteBenef = compteRepository.findByIban(dto.getIbanCompteBeneficiaire())
                .orElseThrow(()-> new IllegalArgumentException("Compte beneficiaire introuvable"));

        if(compteEmet.getSolde() < dto.getMontantVirement()) {
            throw new IllegalArgumentException("Solde insuffisant");
        }

        // Création de la transaction
        Transaction transaction = new Transaction();
        transaction.setTypeTransaction(TypeTransaction.DEBIT);
        transaction.setTypeSource(TypeSource.VIREMENT);
        transaction.setDateTransaction(LocalDateTime.now());
        transaction.setMontantTransaction(dto.getMontantVirement());
        transaction.setCompte(compteEmet);


        // Mise à jour des soldes
        compteEmet.setSolde(compteEmet.getSolde() - dto.getMontantVirement());
        compteBenef.setSolde(compteBenef.getSolde() + dto.getMontantVirement());

        compteRepository.save(compteEmet);
        compteRepository.save(compteBenef);


        // Création du virement avec association de la transaction
        Virement virement = Virement.builder()
                .ibanCompteBeneficiaire(dto.getIbanCompteBeneficiaire())
                .ibanCompteEmetteur(dto.getIbanCompteEmetteur())
                .montantVirement(dto.getMontantVirement())
                .dateVirement(LocalDateTime.now())
                .libelleVirement(dto.getLibelleVirement())
                .transaction(transaction)
                .build();


        virementRepository.save(virement);

        return mapVirementToResponseDTO(virement);
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
