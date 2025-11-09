package com.project.SpringBank.controllers;

import com.project.SpringBank.DTO.carte.CreateCarteRequestDTO;
import com.project.SpringBank.DTO.carte.CreateCarteResponseDTO;
import com.project.SpringBank.DTO.paiement.CreatePaiementRequestDTO;
import com.project.SpringBank.entities.Carte;
import com.project.SpringBank.services.CarteService;
import com.project.SpringBank.services.PaiementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cartes")
@RequiredArgsConstructor
public class CarteController {

    private final CarteService carteService;
    private final PaiementService paiementService;

    // Création d’une carte
    @PostMapping
    public ResponseEntity<CreateCarteResponseDTO> createCard(@RequestBody CreateCarteRequestDTO dto) {
        Carte carte = carteService.createCarte(dto);
        CreateCarteResponseDTO carteDTO = carteService.mapCarteToGetResponseDTO(carte);
        return ResponseEntity.status(HttpStatus.CREATED).body(carteDTO);
    }

    // Liste des cartes d’un compte
    @GetMapping("/account/{compteId}")
    public ResponseEntity<List<CreateCarteResponseDTO>> getCardsByAccount(@PathVariable Long compteId) {
        List<CreateCarteResponseDTO> cartes = carteService.getCartesByCompte(compteId);
        return ResponseEntity.ok(cartes);
    }

    // Effectuer un paiement
    @PostMapping("/{carteId}/paiements")
    public ResponseEntity<CreatePaiementRequestDTO> createPaiement(@PathVariable Long carteId,
                                                                   @RequestBody CreatePaiementRequestDTO dto) {
        CreatePaiementRequestDTO paiementCree = paiementService.createPaiement(dto, carteId);
        return ResponseEntity.status(HttpStatus.CREATED).body(paiementCree);
    }

    // Historique des paiements d’une carte
    @GetMapping("/{carteId}/paiements")
    public ResponseEntity<List<CreatePaiementRequestDTO>> getPaiementsByCarte(@PathVariable String numeroCarte) {
        List<CreatePaiementRequestDTO> paiements = paiementService.getPaymentsByCard(numeroCarte);
        return ResponseEntity.ok(paiements);
    }
}

