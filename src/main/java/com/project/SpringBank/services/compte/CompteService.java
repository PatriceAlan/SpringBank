package com.project.SpringBank.services.compte;

import com.project.SpringBank.entities.Compte;
import com.project.SpringBank.entities.TypeCompte;
import com.project.SpringBank.repositories.CompteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompteService {

    private final CompteRepository compteRepository;

    private static final String CODE_BANQUE = "30003";
    private static final String CODE_GUICHET = "02054";

    public Compte sauvegarderOuMettreAJour(Compte compte) {
        if (compte == null) {
            throw new IllegalArgumentException("Le compte ne peut pas être nul");
        }


        if (compte.getIban() == null || compte.getIban().isEmpty()) {
            String iban = genererIBAN(compte);
            compte.setIban(iban);
        }


        int cleRIB = calculerCleRIB(compte);


        compte.setCleRIB(cleRIB);


        return this.compteRepository.save(compte);

    }


    private String genererIBAN(Compte compte) {
        return "FR76" + CODE_BANQUE + CODE_GUICHET + compte.getNumeroCompte() + compte.getCleRIB();
    }


    private int calculerCleRIB(Compte compte) {
        String numeroCompteAsString = String.valueOf(compte.getNumeroCompte());
        return 97 - (89 * Integer.parseInt(CODE_BANQUE) + 15 * Integer.parseInt(CODE_GUICHET)
                + 3 * Integer.parseInt(numeroCompteAsString)) % 97;
    }

    public Optional<Compte> findById(String iban) {
        if (iban == null) {
            throw new IllegalArgumentException("L'IBAN ne peut pas être nul");
        }
        return compteRepository.findById(iban);
    }

    public List<Compte> listerTousLesComptes() {
        return compteRepository.findAll();
    }

    public List<Compte> trouverComptesParType(TypeCompte typeCompte) {
        return compteRepository.findByTypeCompte(typeCompte);
    }

    public void supprimerCompte(String iban) {
        if (iban == null) {
            throw new IllegalArgumentException("L'IBAN ne peut pas être nul");
        }
        compteRepository.deleteById(iban);
    }
}
