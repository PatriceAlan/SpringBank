package com.project.SpringBank.controllers;

import com.project.SpringBank.entities.Paiement;
import com.project.SpringBank.services.paiement.PaiementDto;
import com.project.SpringBank.services.paiement.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paiements")
public class PaiementController {

    private final PaiementService paiementService;

    @Autowired
    public PaiementController(PaiementService paiementService) {
        this.paiementService = paiementService;
    }

    @PostMapping("/ajouter-paiement")
    public ResponseEntity<Paiement> ajouterOuMettreAJourPaiement(@RequestBody Paiement paiement) {
        try {
            Paiement savedPaiement = paiementService.sauvegarderOuMettreAJourPaiement(paiement);
            return new ResponseEntity<>(savedPaiement, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paiement> rechercherPaiementParId(@PathVariable Long id) {
        try {
            Paiement paiement = paiementService.rechercherPaiement(id);
            return paiement != null
                    ? new ResponseEntity<>(paiement, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerPaiement(@PathVariable Long id) {
        try {
            paiementService.supprimerPaiement(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
