package com.project.SpringBank.services.carte;

import com.project.SpringBank.entities.Carte;
import com.project.SpringBank.entities.Paiement;
import com.project.SpringBank.repositories.CarteRepository;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Builder
public class CarteService {

    private CarteRepository carteRepository;

    public CarteService(CarteRepository carteRepository) {
        this.carteRepository = carteRepository;
    }

    public Carte ajouterCarte(Carte carte) {

        return this.carteRepository.save(Carte.builder()
                .numeroCarte(carte.getNumeroCarte())
                .codeSecurite(carte.getCodeSecurite())
                .dateExpiration(carte.getDateExpiration())
                .titulaireCarte(carte.getTitulaireCarte())
                .compteAssocie(carte.getCompteAssocie())
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
