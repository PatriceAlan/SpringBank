package com.project.SpringBank.services;

import com.project.SpringBank.DTO.paiement.CreatePaiementRequestDTO;
import com.project.SpringBank.DTO.paiement.CreatePaiementResponseDTO;
import com.project.SpringBank.entities.*;
import com.project.SpringBank.mappers.PaiementMapper;
import com.project.SpringBank.repositories.CarteRepository;
import com.project.SpringBank.repositories.CompteRepository;
import com.project.SpringBank.repositories.PaiementRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaiementService {

    private final PaiementRepository paiementRepository;
    private final CarteRepository carteRepository;
    private final CompteRepository compteRepository;
    private final PaiementMapper paiementMapper;

    @Transactional
    public CreatePaiementResponseDTO createPaiement(CreatePaiementRequestDTO dto, Long numeroCarte) {
        Carte carte = carteRepository.findByNumeroCarte(numeroCarte)
                .orElseThrow(() -> new IllegalArgumentException("Carte introuvable"));

        Compte compte = carte.getCompteAssocie();
        if (compte.getSolde() < dto.getMontantPaiement()) {
            throw new IllegalArgumentException("Solde insuffisant");
        }

        // Mise à jour du solde
        compte.setSolde(compte.getSolde() - dto.getMontantPaiement());
        compteRepository.save(compte);

        // Transaction associée
        Transaction transaction = Transaction.builder()
                .typeTransaction(TypeTransaction.DEBIT)
                .typeSource(TypeSource.CARTE)
                .dateTransaction(LocalDateTime.now())
                .montantTransaction(dto.getMontantPaiement())
                .libelleTransaction("Paiement carte " + carte.getNumeroCarte())
                .compte(compte)
                .build();

        // Paiement
        PaiementCarte paiementCarte = PaiementCarte.builder()
                .carte(carte)
                .transaction(transaction)
                .build();

        paiementRepository.save(paiementCarte);

        return paiementMapper.toCreatePaiementResponseDTO(paiementCarte);
    }

}
