package com.project.SpringBank.controllers;

import com.project.SpringBank.DTO.carte.CreateCarteDTO;
import com.project.SpringBank.DTO.carte.ResponseCarteDTO;
import com.project.SpringBank.DTO.compte.CreateCompteDTO;
import com.project.SpringBank.DTO.compte.ResponseCompteDTO;
import com.project.SpringBank.DTO.paiement.ResponsePaiementDTO;
import com.project.SpringBank.entities.*;
import com.project.SpringBank.repositories.CarteRepository;
import com.project.SpringBank.repositories.CompteRepository;
import com.project.SpringBank.services.CarteService;
import com.project.SpringBank.services.CompteService;
import com.project.SpringBank.services.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/comptes")
public class CompteController {

    private final CompteService compteService;
    private final CompteRepository compteRepository;
    private final CarteService carteService;
    private final CarteRepository carteRepository;
    private final PaiementService paiementService;

    @Autowired
    public CompteController(CompteService compteService, CompteRepository compteRepository, CarteService carteService,
                            CarteRepository carteRepository, PaiementService paiementService) {
        this.compteService = compteService;
        this.compteRepository = compteRepository;
        this.carteService = carteService;
        this.carteRepository = carteRepository;
        this.paiementService = paiementService;
    }

    @PostMapping
    public ResponseEntity<ResponseCompteDTO> createCompte(@RequestBody CreateCompteDTO compteDTO) {
        Compte createdCompte = compteService.createCompte(compteDTO);
        ResponseCompteDTO responseCompteDTO = mapCompteToResponseDTO(createdCompte);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseCompteDTO);
    }


    @GetMapping
    public ResponseEntity<List<ResponseCompteDTO>> listComptes() {
        List<ResponseCompteDTO> comptes = compteService.listComptes();
        return ResponseEntity.ok(comptes);
    }

    public ResponseCompteDTO mapCompteToResponseDTO(Compte compte){

        Set<Long> titulaires = new HashSet<>();
        for (Client client : compte.getTitulaireCompte()) {
            titulaires.add(client.getIdClient());
        }
        return ResponseCompteDTO.builder()
                .iban(compte.getIban())
                .numeroCompte(compte.getNumeroCompte())
                .typeCompte(compte.getTypeCompte().name())
                .titulaireCompte(titulaires)
                .intituleCompte(compte.getIntituleCompte())
                .dateCreation(compte.getDateCreation())
                .build();
    }

    @GetMapping("/{iban}")
    public ResponseEntity<ResponseCompteDTO> getCompteByIban(@PathVariable String iban) {
        Optional<Compte> compteOptional = compteRepository.findByIban(iban);
        Compte compte = compteOptional.orElse(null);
        assert compte != null;
        ResponseCompteDTO responseCompteDTO = compteService.mapCompteToResponseDTO(compte);
        return ResponseEntity.ok(responseCompteDTO);
    }

    @GetMapping("/{iban}/titulaires")
    public Set<Client> getTitulairesByIban(@PathVariable String iban) {
        Compte compte = compteRepository.findById(iban).orElseThrow();
        return compte.getTitulaireCompte();
    }

    @GetMapping("/{iban}/cartes")
    public List<ResponseCarteDTO> getCartesByIban(@PathVariable String iban) {
        Compte compte = compteRepository.findById(iban).orElseThrow();
        List<Carte> cartes = compte.getCartes();
        List<ResponseCarteDTO> cartesDTO = new ArrayList<>();
        for (Carte carte : cartes) {
            ResponseCarteDTO carteDTO = new ResponseCarteDTO();
            carteDTO.setTitulaireCarte(carte.getTitulaireCarte().getIdClient());
            carteDTO.setNumeroCarte(carte.getNumeroCarte());
            carteDTO.setDateExpiration(carte.getDateExpiration());

            cartesDTO.add(carteDTO);
        }

        return cartesDTO;
    }

    @PostMapping("/{iban}/cartes")
    public ResponseEntity<ResponseCarteDTO> createCarteForCompte(@PathVariable String iban, @RequestBody CreateCarteDTO carteDTO) {

        // Vérifiez si le compte avec l'IBAN donné existe
        Compte compte = compteRepository.findByIban(iban).orElse(null);
        if (compte == null) {

            System.out.println("Le compte avec l'IBAN donné n'existe pas");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Créer une carte pour le compte
        Carte carte = carteService.createCarte(carteDTO, iban);


        // Mapper la carte créée à votre DTO de réponse pour la carte
        ResponseCarteDTO responseCarteDTO = carteService.mapCarteToResponseDTO(carte);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseCarteDTO);
    }

    @PostMapping("/{iban}/cartes/{numeroCarte}/paiement")
    public ResponseEntity<ResponsePaiementDTO> createPaiementForCarte(@PathVariable String iban, @PathVariable Long numeroCarte, @RequestBody ResponsePaiementDTO paiementDTO) {

        // Vérifiez si le compte avec l'IBAN donné existe
        Compte compte = compteRepository.findById(iban).orElse(null);
        if (compte == null) {

            System.out.println("Le compte avec l'IBAN donné n'existe pas");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Vérifiez si la carte avec le numéro de carte donné existe
        Carte carte = carteRepository.findById(numeroCarte).orElse(null);
        if (carte == null) {

            System.out.println("La carte avec le numéro donné n'existe pas");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        double montantPaiement = paiementDTO.getMontantPaiement();
        double solde = compte.getSolde();

        if (montantPaiement > solde) {

            System.out.println("Le montant du paiement est supérieur au solde du compte");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Paiement paiement = paiementService.createPaiement(paiementDTO);

        paiement.setCarte(carte);

        ResponsePaiementDTO responsePaiementDTO = paiementService.mapPaiementToResponseDTO(paiement);

        return ResponseEntity.status(HttpStatus.CREATED).body(responsePaiementDTO);
    }


}
