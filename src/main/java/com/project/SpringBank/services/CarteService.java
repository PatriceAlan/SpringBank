package com.project.SpringBank.services;

import com.project.SpringBank.DTO.carte.CreateCarteRequestDTO;
import com.project.SpringBank.DTO.carte.CreateCarteResponseDTO;
import com.project.SpringBank.DTO.carte.GetCarteResponseDTO;
import com.project.SpringBank.entities.Carte;
import com.project.SpringBank.entities.Client;
import com.project.SpringBank.entities.Compte;
import com.project.SpringBank.repositories.CarteRepository;
import com.project.SpringBank.repositories.ClientRepository;
import com.project.SpringBank.repositories.CompteRepository;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.project.SpringBank.Utils.FonctionsGeneratives.genererCodeSecurite;
import static com.project.SpringBank.Utils.FonctionsGeneratives.genererNumeroCompte;

@Service
@Builder
public class CarteService {

    private final CarteRepository carteRepository;
    private final ClientRepository clientRepository;
    private final CompteRepository compteRepository;

    public Carte createCarte(String iban, CreateCarteRequestDTO createCarteRequestDTO) {
        Compte compte = compteRepository.findByIban(iban)
                .orElseThrow(() -> new IllegalArgumentException("Compte introuvable"));

        Client client = clientRepository.findById(createCarteRequestDTO.getTitulaireCarte())
                .orElseThrow(() -> new IllegalArgumentException("Client introuvable"));

        Carte carte = Carte.builder()
                .numeroCarte(genererNumeroCompteUnique(compteRepository))
                .dateExpiration(LocalDateTime.now().plusYears(3))
                .active(true)
                .compteAssocie(compte)
                .titulaireCarte(client)
                .build();

        return carteRepository.save(carte);
    }

    public long genererNumeroCompteUnique(CompteRepository compteRepository) {
        long numero;
        do {
            numero = genererNumeroCompte();
        } while (compteRepository.existsByNumeroCompte(numero));
        return numero;
    }


}
