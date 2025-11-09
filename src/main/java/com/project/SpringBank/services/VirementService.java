package com.project.SpringBank.services;

import com.project.SpringBank.DTO.virement.CreateVirementRequestDTO;
import com.project.SpringBank.DTO.virement.CreateVirementResponseDTO;
import com.project.SpringBank.entities.*;
import com.project.SpringBank.mappers.VirementMapper;
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
    private final VirementMapper virementMapper;

    @Transactional
    public CreateVirementResponseDTO createVirement(CreateVirementRequestDTO virementDTO) {

        Compte compteEmet = compteRepository.findByIban(virementDTO.getIbanCompteEmetteur())
                .orElseThrow(()-> new IllegalArgumentException("Compte emetteur introuvable"));
        Compte compteBenef = compteRepository.findByIban(virementDTO.getIbanCompteReceveur())
                .orElseThrow(()-> new IllegalArgumentException("Compte beneficiaire introuvable"));

        if(compteEmet.getSolde() < virementDTO.getMontantVirement()) {
            throw new IllegalArgumentException("Solde insuffisant");
        }

        // Mise à jour des soldes
        compteEmet.setSolde(compteEmet.getSolde() - virementDTO.getMontantVirement());
        compteBenef.setSolde(compteBenef.getSolde() + virementDTO.getMontantVirement());

        Transaction debit = Transaction.builder()
                .dateTransaction(LocalDateTime.now())
                .typeTransaction(TypeTransaction.DEBIT)
                .typeSource(TypeSource.VIREMENT)
                .montantTransaction(virementDTO.getMontantVirement())
                .libelleTransaction("Virement vers "+ virementDTO.getIbanCompteReceveur())
                .compte(compteEmet)
                .build();

        Transaction credit = Transaction.builder()
                .dateTransaction(LocalDateTime.now())
                .typeTransaction(TypeTransaction.CREDIT)
                .typeSource(TypeSource.VIREMENT)
                .montantTransaction(virementDTO.getMontantVirement())
                .libelleTransaction("Virement reçu de "+ virementDTO.getIbanCompteEmetteur())
                .compte(compteBenef)
                .build();

        Virement sendVirement = Virement.builder()
                .ibanCompteEmetteur(virementDTO.getIbanCompteEmetteur())
                .ibanCompteReceveur(virementDTO.getIbanCompteReceveur())
                .libelleVirement(virementDTO.getLibelleVirement())
                .dateVirement(LocalDateTime.now())
                .montantVirement(virementDTO.getMontantVirement())
                .transaction(debit)
                .build();

        Virement receiveVirement = Virement.builder()
                .ibanCompteEmetteur(virementDTO.getIbanCompteEmetteur())
                .ibanCompteReceveur(virementDTO.getIbanCompteReceveur())
                .libelleVirement(virementDTO.getLibelleVirement())
                .dateVirement(LocalDateTime.now())
                .montantVirement(virementDTO.getMontantVirement())
                .transaction(credit)
                .build();

        debit.setVirement(sendVirement);
        credit.setVirement(receiveVirement);

        virementRepository.save(sendVirement);
        virementRepository.save(receiveVirement);

        return virementMapper.toVirementResponseDto(sendVirement);
    }


    public List<CreateVirementResponseDTO> listerVirements() {
        return this.virementRepository.findAll().stream()
                .map(virementMapper::toVirementResponseDto)
                .toList();
    }

}
