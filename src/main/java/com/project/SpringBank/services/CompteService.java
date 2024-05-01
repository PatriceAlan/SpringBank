package com.project.SpringBank.services;

import com.project.SpringBank.DTO.compte.CreateCompteDTO;
import com.project.SpringBank.DTO.compte.ResponseCompteDTO;
import com.project.SpringBank.entities.*;
import com.project.SpringBank.repositories.ClientRepository;
import com.project.SpringBank.repositories.CompteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Builder
public class CompteService {

    private final CompteRepository compteRepository;
    private final ClientService clientService;

    private static final String CODE_BANQUE = "30003";
    private static final String CODE_GUICHET = "02054";

    public CompteService(CompteRepository compteRepository, ClientService clientService) {
        this.compteRepository = compteRepository;
        this.clientService = clientService;
    }

    public Compte createCompte(CreateCompteDTO createCompteDTO) {

        Compte compte = Compte.builder()
                .numeroCompte(createCompteDTO.getNumeroCompte())
                .solde(createCompteDTO.getSolde())
                .cleRIB(calculerCleRIB(createCompteDTO))
                .typeCompte(createCompteDTO.getTypeCompte())
                .titulaireCompte(new HashSet<>(createCompteDTO.getTitulaireCompte()))
                .intituleCompte(createCompteDTO.getIntituleCompte())
                .dateCreation(LocalDateTime.now())
                .iban(genererIBAN(createCompteDTO))
                .build();

        return compteRepository.save(compte);
    }


    public ResponseCompteDTO mapCompteToResponseDTO(Compte compte){
        return ResponseCompteDTO.builder()
                .iban(compte.getIban())
                .numeroCompte(compte.getNumeroCompte())
                .typeCompte(compte.getTypeCompte().name())
                .titulaireCompte(compte.getTitulaireCompte())
                .intituleCompte(compte.getIntituleCompte())
                .dateCreation(compte.getDateCreation())
                .build();
    }

    public List<ResponseCompteDTO> listComptes(){
        List<Compte> comptes = compteRepository.findAll();
        return comptes.stream()
                .map(this::mapCompteToResponseDTO)
                .toList();
    }

    private String genererIBAN(CreateCompteDTO compte) {
        return "FR76" + CODE_BANQUE + CODE_GUICHET + compte.getNumeroCompte() + compte.getCleRIB();
    }


    private int calculerCleRIB(CreateCompteDTO compte) {
        String numeroCompteAsString = String.valueOf(compte.getNumeroCompte());
        return 97 - (89 * Integer.parseInt(CODE_BANQUE) + 15 * Integer.parseInt(CODE_GUICHET)
                + 3 * Integer.parseInt(numeroCompteAsString)) % 97;
    }

    public Set<Client> getTitulaireCompte(String iban) {
        Optional<Compte> compte = compteRepository.findById(iban);
        if (compte.isEmpty()) {
            throw new EntityNotFoundException("Le compte n'existe pas");
        }
        return compte.get().getTitulaireCompte();
    }


}
