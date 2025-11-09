package com.project.SpringBank.controllers;

import com.project.SpringBank.DTO.carte.CreateCarteRequestDTO;
import com.project.SpringBank.DTO.carte.CreateCarteResponseDTO;
import com.project.SpringBank.DTO.compte.CreateCompteRequestDTO;
import com.project.SpringBank.DTO.compte.CreateCompteResponseDTO;
import com.project.SpringBank.DTO.compte.GetComptesResponseDTO;
import com.project.SpringBank.entities.Carte;
import com.project.SpringBank.entities.Client;
import com.project.SpringBank.entities.Compte;
import com.project.SpringBank.entities.Transaction;
import com.project.SpringBank.repositories.CarteRepository;
import com.project.SpringBank.repositories.CompteRepository;
import com.project.SpringBank.services.CarteService;
import com.project.SpringBank.services.CompteService;
import com.project.SpringBank.services.PaiementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/comptes")
@RequiredArgsConstructor
public class CompteController {

    private final CompteService compteService;
    private final CompteRepository compteRepository;
    private final CarteService carteService;
    private final CarteRepository carteRepository;
    private final PaiementService paiementService;

    @GetMapping
    public ResponseEntity<List<GetComptesResponseDTO>> listComptes() {
        return ResponseEntity.ok(compteService.listComptes());
    }

    @GetMapping("/{iban}")
    public ResponseEntity<CreateCompteResponseDTO> getCompteByIban(@PathVariable String iban){
        Optional<Compte> optionalCompte = compteRepository.findByIban(iban);
        if (optionalCompte.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Compte compte = optionalCompte.get();
        CreateCompteResponseDTO compteDTO = compteService.mapCompteToResponseDTO(compte);
        return ResponseEntity.ok(compteDTO);
    }

    public CreateCompteResponseDTO mapCompteToResponseDTO(Compte compte){
        Set<Long> titulaires = new HashSet<>();
        for (Client client : compte.getTitulaires()) {
            titulaires.add(client.getIdClient());
        }
        return CreateCompteResponseDTO.builder()
                .iban(compte.getIban())
                .typeCompte(compte.getTypeCompte())
                .titulaireCompte(titulaires)
                .intituleCompte(compte.getIntituleCompte())
                .dateCreation(compte.getDateCreation())
                .build();
    }

    @PostMapping
    public ResponseEntity<CreateCompteResponseDTO> createCompte(@RequestBody CreateCompteRequestDTO compteDTO) {
        Compte compteCree = compteService.createCompte(compteDTO);
        CreateCompteResponseDTO createCompteResponseDTO = mapCompteToResponseDTO(compteCree);
        return ResponseEntity.status(HttpStatus.CREATED).body(createCompteResponseDTO);
    }


    @GetMapping("/{iban}/titulaires")
    public ResponseEntity<Set<Client>> getTitulairesCompte(@PathVariable String iban){
        Optional<Compte> optionalCompte = compteRepository.findByIban(iban);
        if (optionalCompte.isEmpty()){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Compte compte = optionalCompte.get();
        Set<Client> clients = compte.getTitulaires();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{iban}/transactions")
    public ResponseEntity<List<Transaction>> getTransactionsCompte(@PathVariable String iban){
        Optional<Compte> optionalCompte = compteRepository.findByIban(iban);
        if (optionalCompte.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Compte compte = optionalCompte.get();
        List<Transaction> transactions = compte.getTransactions();
        return ResponseEntity.ok(transactions);

    }

    @PostMapping("{iban}/cartes")
    public ResponseEntity<CreateCarteResponseDTO> createCarteForCompte(
            @PathVariable String iban,
            @RequestBody CreateCarteRequestDTO carteDTO) {

        Optional<Compte> optionalCompte = compteRepository.findByIban(iban);
        if (optionalCompte.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Carte carte = carteService.createCarte(carteDTO);
        CreateCarteResponseDTO createCarteResponseDTO = carteService.mapCarteToGetResponseDTO(carte);
        return ResponseEntity.status(HttpStatus.CREATED).body(createCarteResponseDTO);
    }


}
