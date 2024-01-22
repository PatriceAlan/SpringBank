package com.project.SpringBank.services.virement;

import com.project.SpringBank.entities.Virement;
import com.project.SpringBank.repositories.VirementRepository;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Builder
public class VirementService {

    private final VirementRepository virementRepository;

    public Virement sauvegarderOuMettreAJourVirement(Virement virement) {
        if (virement == null) {
            throw new IllegalArgumentException("Le virement ne peut pas être nul");
        }

        return this.virementRepository.save(Virement.builder()
                .idVirement(virement.getIdVirement())
                .montantVirement(virement.getMontantVirement())
                .dateVirement(virement.getDateVirement())
                .build());
    }

    public Virement rechercherVirement(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID ne peut pas être nul");
        }

        return this.virementRepository.findById(id).orElse(null);
    }

    public List<Virement> listerVirementsEmisDuCompte(String iban) {
        if (iban == null || iban.isEmpty()) {
            throw new IllegalArgumentException("L'iban ne peut pas être nul ou vide");
        }

        return this.virementRepository.findAllByCompteDebiteur_Iban(iban);
    }

    public List<Virement> listerVirementsRecusSurLeCompte(String iban) {
        if (iban == null || iban.isEmpty()) {
            throw new IllegalArgumentException("L'iban ne peut pas être nul ou vide");
        }

        return this.virementRepository.findAllByCompteCrediteur_Iban(iban);
    }

    public List<Virement> listerTousLesVirementsDuCompte(String iban) {
        if (iban == null || iban.isEmpty()) {
            throw new IllegalArgumentException("L'iban ne peut pas être nul ou vide");
        }

        List<Virement> virementsDuCompteEmis = this.listerVirementsEmisDuCompte(iban);
        List<Virement> virementsDuCompteRecus = this.listerVirementsRecusSurLeCompte(iban);


        virementsDuCompteEmis.addAll(virementsDuCompteRecus);

        return virementsDuCompteEmis;
    }



}
