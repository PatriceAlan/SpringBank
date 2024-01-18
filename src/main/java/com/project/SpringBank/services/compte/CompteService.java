package com.project.SpringBank.services.compte;

import com.project.SpringBank.entities.Compte;
import com.project.SpringBank.repositories.CompteRepository;
import lombok.Builder;
import org.springframework.stereotype.Service;

@Service
@Builder
public class CompteService {

    private CompteRepository compteRepository;

    public CompteService(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    public Compte sauvegarderOuMettreAJour(Compte compte) {

        return this.compteRepository.save(Compte.builder()
                .iban(compte.getIban())
                .numeroCompte(compte.getNumeroCompte())
                .solde(compte.getSolde())
                .typeCompte(compte.getTypeCompte())
                .intituleCompte(compte.getIntituleCompte())
                .build());
    }
}
