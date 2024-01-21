package com.project.SpringBank.services.paiement;

import com.project.SpringBank.entities.Paiement;
import com.project.SpringBank.repositories.PaiementRepository;
import lombok.Builder;
import org.springframework.stereotype.Service;

@Service
@Builder
public class PaiementService {

    private final PaiementRepository paiementRepository;

    public Paiement sauvegarderOuMettreAJourPaiement(Paiement paiement) {
        if (paiement == null) {
            throw new IllegalArgumentException("Le paiement ne peut pas être nul");
        }

        return this.paiementRepository.save(Paiement.builder()
                .idPaiement(paiement.getIdPaiement())
                        .codeSecurite(paiement.getCodeSecurite())
                .montantPaiement(paiement.getMontantPaiement())
                .datePaiement(paiement.getDatePaiement()).build());
    }

    public Paiement rechercherPaiement(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID ne peut pas être nul");
        }

        return this.paiementRepository.findById(id).orElse(null);
    }

    public void supprimerPaiement(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID ne peut pas être nul");
        }

        this.paiementRepository.deleteById(id);
    }



}
