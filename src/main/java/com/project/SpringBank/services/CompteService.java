package com.project.SpringBank.services;

import com.project.SpringBank.DTO.carte.GetCarteResponseDTO;
import com.project.SpringBank.DTO.compte.CreateCompteRequestDTO;
import com.project.SpringBank.DTO.compte.GetComptesResponseDTO;
import com.project.SpringBank.entities.Carte;
import com.project.SpringBank.entities.Client;
import com.project.SpringBank.entities.Compte;
import com.project.SpringBank.mappers.CarteMapper;
import com.project.SpringBank.mappers.CompteMapper;
import com.project.SpringBank.repositories.CarteRepository;
import com.project.SpringBank.repositories.ClientRepository;
import com.project.SpringBank.repositories.CompteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

import static com.project.SpringBank.Utils.FonctionsGeneratives.genererIban;
import static com.project.SpringBank.Utils.FonctionsGeneratives.genererNumeroCompte;

@Service
@RequiredArgsConstructor
public class CompteService {

    private final CompteRepository compteRepository;
    private final ClientRepository clientRepository;
    private final CarteRepository carteRepository;
    private final CompteMapper compteMapper;
    private final CarteMapper carteMapper;


    public Compte createCompte(CreateCompteRequestDTO compteCree) {

        // Recherche des clients par leur ID
        Set<Client> titulaires = new HashSet<>();
        for (Long clientId : compteCree.getTitulairesCompte()) {
            Optional<Client> existingClient = clientRepository.getClientByIdClient(clientId);
            if (existingClient.isEmpty()){
                throw new IllegalArgumentException("Le client avec l'ID " + clientId + " est introuvable.");
            }
            titulaires.add(existingClient.get());
        }

        Long numeroCompteCree = genererNumeroCompte();
        String ibanCree = genererIban(numeroCompteCree);

        Compte compte = Compte.builder()
                .iban(ibanCree)
                .numeroCompte(numeroCompteCree)
                .solde(compteCree.getSolde())
                .typeCompte(compteCree.getTypeCompte())
                .intituleCompte(compteCree.getIntituleCompte())
                .dateCreation(LocalDateTime.now())
                .titulairesCompte(titulaires)
                .build();

        return compteRepository.save(compte);
    }


    public List<GetComptesResponseDTO> listComptesClient(Client titulairesCompte){
        List<Compte> comptes = compteRepository.findByTitulairesCompte(titulairesCompte);
        return comptes.stream()
                .map(compteMapper::toGetComptesResponseDTO)
                .toList();
    }

    public List<GetCarteResponseDTO> listCartesCompte(String iban){
        List<Carte> cartes = carteRepository.findByCompteAssocie_Iban(iban);

        return cartes.stream()
                .map(carteMapper::toGetCarteResponseDTO)
                .toList();
    }


}
