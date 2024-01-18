package com.project.SpringBank.services.carte;

import com.project.SpringBank.entities.Carte;
import com.project.SpringBank.repositories.CarteRepository;
import lombok.Builder;
import org.springframework.stereotype.Service;

@Service
@Builder
public class CarteService {

    private CarteRepository carteRepository;

    public CarteService(CarteRepository carteRepository) {
        this.carteRepository = carteRepository;
    }

    public Carte sauvegarderOuMettreAJour(Carte carte) {

        return this.carteRepository.save(Carte.builder()
                .numeroCarte(carte.getNumeroCarte())
                .codeSecurite(carte.getCodeSecurite())
                .dateExpiration(carte.getDateExpiration())
                .titulaireCarte(carte.getTitulaireCarte())
                .compteAssocie(carte.getCompteAssocie())
                .paiements(carte.getPaiements())
                .build());
    }



}
