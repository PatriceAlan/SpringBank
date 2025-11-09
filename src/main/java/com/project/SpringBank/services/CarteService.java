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

    public Carte createCarte(CreateCarteRequestDTO dto) {
        Compte compte = compteRepository.findById(dto.)
                .orElseThrow(() -> new IllegalArgumentException("Compte introuvable"));

        Client client = clientRepository.findById(dto.getTitulaireId())
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

    public List<GetCarteResponseDTO> getCartesByCompte(Long compteId) {
        List<Carte> cartes = carteRepository.findByCompteAssocie(compteId);
        return cartes.stream().map(this::mapCarteToGetResponseDTO).toList();
    }

    public GetCarteResponseDTO mapCarteToGetResponseDTO(Carte carte) {
        return GetCarteResponseDTO.builder()
                .numeroCarte(carte.getNumeroCarte())
                .dateExpiration(carte.getDateExpiration())
                .titulaireCarte(carte.getTitulaireCarte().getIdClient())
                .build();
    }

    public CreateCarteRequestDTO mapCreateCarteToResponseDTO(Carte carte) {
        return CreateCarteRequestDTO.builder()
                .titulaireCarte(carte.getTitulaireCarte().getIdClient())
                .codeSecurite(genererCodeSecurite())
                .build();
    }

    public long genererNumeroCompteUnique(CompteRepository compteRepository) {
        long numero;
        do {
            numero = genererNumeroCompte();
        } while (compteRepository.existsByNumeroCompte(numero));
        return numero;
    }


}
