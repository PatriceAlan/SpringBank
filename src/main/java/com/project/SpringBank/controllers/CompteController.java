package com.project.SpringBank.controllers;

import com.project.SpringBank.DTO.HttpErrorResponse;
import com.project.SpringBank.DTO.carte.CreateCarteRequestDTO;
import com.project.SpringBank.DTO.carte.CreateCarteResponseDTO;
import com.project.SpringBank.DTO.carte.GetCarteResponseDTO;
import com.project.SpringBank.DTO.compte.CreateCompteRequestDTO;
import com.project.SpringBank.DTO.compte.CreateCompteResponseDTO;
import com.project.SpringBank.DTO.compte.GetComptesResponseDTO;
import com.project.SpringBank.DTO.paiement.CreatePaiementRequestDTO;
import com.project.SpringBank.DTO.paiement.CreatePaiementResponseDTO;
import com.project.SpringBank.entities.Carte;
import com.project.SpringBank.entities.Client;
import com.project.SpringBank.entities.Compte;
import com.project.SpringBank.mappers.CarteMapper;
import com.project.SpringBank.mappers.CompteMapper;
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
    private final CompteMapper compteMapper;
    private final CarteMapper carteMapper;
    private final CarteRepository carteRepository;
    private final PaiementService paiementService;

    @GetMapping
    public ResponseEntity<?> listComptesClient(@RequestParam Client titulairesCompte) {

        if (titulairesCompte == null){
            HttpErrorResponse errorResponse = new HttpErrorResponse(
                    "L'identifiant est obligatoire."
            );
            return ResponseEntity.badRequest().body(errorResponse);
        }
        List<GetComptesResponseDTO> comptesResponseDTOS = compteService.listComptesClient(titulairesCompte);

        if (comptesResponseDTOS.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(comptesResponseDTOS);
    }

    @PostMapping
    public ResponseEntity<CreateCompteResponseDTO> createCompteClients(@RequestBody CreateCompteRequestDTO compteDTO) {
        Compte compteCree = compteService.createCompte(compteDTO);
        CreateCompteResponseDTO createCompteResponseDTO = compteMapper.createCompteResponseDTO(compteCree);
        return ResponseEntity.status(HttpStatus.CREATED).body(createCompteResponseDTO);
    }

    @GetMapping("{iban}/cartes")
    public ResponseEntity<?> listCartesCompte(@RequestParam String iban) {

        if (iban == null || iban.isBlank()){
            HttpErrorResponse errorResponse = new HttpErrorResponse(
                    "L'iban est obligatoire."
            );
            return ResponseEntity.badRequest().body(errorResponse);
        }
        List<GetCarteResponseDTO> carteResponseDTOs = compteService.listCartesCompte(iban);

        if (carteResponseDTOs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(carteResponseDTOs);
    }

    @PostMapping("{iban}/cartes")
    public ResponseEntity<CreateCarteResponseDTO> createCarteForCompte(
            @PathVariable String iban,
            @RequestBody CreateCarteRequestDTO carteDTO) {

        Optional<Compte> optionalCompte = compteRepository.findByIban(iban);
        if (optionalCompte.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Carte carte = carteService.createCarte(iban, carteDTO);
        CreateCarteResponseDTO createCarteResponseDTO = carteMapper.toCreateCarteResponseDTO(carte);
        return ResponseEntity.status(HttpStatus.CREATED).body(createCarteResponseDTO);
    }

    @PostMapping("{iban}/cartes/{numeroCarte}/paiement")
    public ResponseEntity<?> paiementByCarte(
            @PathVariable String iban,
            @PathVariable Long numeroCarte,
            @RequestBody CreatePaiementRequestDTO paiementRequestDTO) {

        Optional<Carte> optionalCarte = carteRepository.findByNumeroCarte(numeroCarte);
        if (optionalCarte.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        CreatePaiementResponseDTO paiementCarteResponse = paiementService.createPaiement(paiementRequestDTO, numeroCarte);
        return ResponseEntity.status(HttpStatus.CREATED).body(paiementCarteResponse);


    }










}
