package com.project.SpringBank.services;

import com.project.SpringBank.DTO.virement.CreateVirementRequestDTO;
import com.project.SpringBank.DTO.virement.CreateVirementResponseDTO;
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
    public CreateVirementResponseDTO createVirement(CreateVirementRequestDTO dto) {

        var compteEmet = compteRepository.findByIban(dto.getIbanCompteEmetteur())
                .orElseThrow(()-> new IllegalArgumentException("Compte emetteur introuvable"));
        var compteBenef = compteRepository.findByIban(dto.getIbanCompteBeneficiaire())
                .orElseThrow(()-> new IllegalArgumentException("Compte beneficiaire introuvable"));

        if(compteEmet.getSolde() < dto.getMontantVirement()) {
            throw new IllegalArgumentException("Solde insuffisant");
        }

        // Mise à jour des soldes
        compteEmet.setSolde(compteEmet.getSolde() - dto.getMontantVirement());
        compteBenef.setSolde(compteBenef.getSolde() + dto.getMontantVirement());

        Transaction debit = Transaction.builder()
                .dateTransaction(LocalDateTime.now())
                .typeTransaction(TypeTransaction.DEBIT)
                .typeSource(TypeSource.VIREMENT)
                .montantTransaction(dto.getMontantVirement())
                .libelleTransaction("Virement vers "+ dto.getIbanCompteBeneficiaire())
                .ibanCompteConcerne(dto.getIbanCompteBeneficiaire())
                .compte(compteEmet)
                .build();

        Transaction  credit = Transaction.builder()
                .dateTransaction(LocalDateTime.now())
                .typeTransaction(TypeTransaction.CREDIT)
                .typeSource(TypeSource.VIREMENT)
                .montantTransaction(dto.getMontantVirement())
                .libelleTransaction("Virement reçu de "+ dto.getIbanCompteEmetteur())
                .ibanCompteConcerne(dto.getIbanCompteEmetteur())
                .compte(compteBenef)
                .build();

        Virement virement = Virement.builder()
                .libelleVirement(dto.getLibelleVirement())
                .dateVirement(LocalDateTime.now())
                .montant(dto.getMontantVirement())
                .transactions(List.of(debit, credit))
                .build();

        compteRepository.save(compteEmet);
        compteRepository.save(compteBenef);
        virementRepository.save(virement);

        return mapVirementToResponseDTO(virement);
    }

    public CreateVirementResponseDTO mapVirementToResponseDTO(Virement virement) {
        return CreateVirementResponseDTO.builder()
                .idVirement(virement.getIdVirement())
                .dateVirement(virement.getDateVirement())
                .montant(virement.getMontant())
                .transactions(virement.getTransactions())
                .build();
    }

    public List<CreateVirementResponseDTO> listerVirements() {
        return this.virementRepository.findAll().stream()
                .map(this::mapVirementToResponseDTO)
                .toList();
    }

}
