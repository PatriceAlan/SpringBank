package com.project.SpringBank.services.carte;

import com.project.SpringBank.entities.Carte;
import com.project.SpringBank.entities.Compte;
import com.project.SpringBank.entities.Paiement;
import com.project.SpringBank.repositories.CarteRepository;
import com.project.SpringBank.services.compte.CompteService;
import jakarta.persistence.EntityNotFoundException;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Builder
public class CarteService {

    private final CarteRepository carteRepository;
    private final CompteService compteService;

    @Autowired
    public CarteService(CarteRepository carteRepository, CompteService compteService) {
        this.carteRepository = carteRepository;
        this.compteService = compteService;
    }

    public Carte ajouterCarte(Carte carte, String ibanTitulaireCarte) {
        if (carte == null || ibanTitulaireCarte == null || ibanTitulaireCarte.isEmpty()) {
            throw new IllegalArgumentException("La carte ou l'IBAN du titulaire ne peut pas être nulle ou vide");
        }

        // Utilisation du service injecté pour rechercher le compte du titulaire
        Compte compteTitulaire = compteService.rechercherCompte(ibanTitulaireCarte)
                .orElseThrow(() -> new EntityNotFoundException("Compte du titulaire non trouvé pour l'IBAN : " + ibanTitulaireCarte));

        // Création de la carte avec le titulaire de la carte trouvé
        return this.carteRepository.save(Carte.builder()
                .numeroCarte(carte.getNumeroCarte())
                .codeSecurite(carte.getCodeSecurite())
                .dateExpiration(carte.getDateExpiration())
                .titulaireCarte(compteTitulaire.getClients().iterator().next())
                .compteAssocie(compteTitulaire)
                .paiements(carte.getPaiements())
                .build());
    }



    public Carte rechercherCarte(Long numeroCarte) {

        if (numeroCarte== null) {
            throw new IllegalArgumentException("Le numéro de carte ne peut pas être nul");
        }

        return this.carteRepository.findById(numeroCarte).orElse(null);
    }

    public void supprimerCarte(Long numeroCarte) {
        if (numeroCarte == null) {
            throw new IllegalArgumentException("Le numéro de carte ne peut pas être nul");
        }

        this.carteRepository.deleteById(numeroCarte);
    }

    public List<Carte> listerCartesDuCompte(String iban) {
        if (iban == null || iban.isEmpty()) {
            throw new IllegalArgumentException("L'iban ne peut pas être nul ou vide");
        }

        return this.carteRepository.findAllByCompteAssocie_Iban(iban);
    }

    public List<Paiement> listerPaiementsDeLaCarte(Long numeroCarte) {
        if (numeroCarte == null) {
            throw new IllegalArgumentException("Le numéro de carte ne peut pas être nul");
        }

        Carte carte = this.carteRepository.findById(numeroCarte).orElse(null);

        if (carte == null) {
            throw new IllegalArgumentException("La carte n'existe pas");
        }

        return carte.getPaiements();
    }


}
