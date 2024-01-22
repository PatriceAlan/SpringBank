package com.project.SpringBank.controllers;

import com.project.SpringBank.entities.Carte;
import com.project.SpringBank.entities.Paiement;
import com.project.SpringBank.services.carte.CarteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartes")
public class CarteController {

    private final CarteService carteService;

    @Autowired
    public CarteController(CarteService carteService) {
        this.carteService = carteService;
    }

    @PostMapping("/ajouter-carte")
    public ResponseEntity<Carte> ajouterCarte(@RequestBody Carte carte, @RequestParam String ibanTitulaireCarte) {
        try {
            Carte addedCarte = carteService.ajouterCarte(carte, ibanTitulaireCarte);
            return new ResponseEntity<>(addedCarte, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{numeroCarte}")
    public ResponseEntity<Carte> rechercherCarteParNumero(@PathVariable Long numeroCarte) {
        try {
            Carte carte = carteService.rechercherCarte(numeroCarte);
            return carte != null
                    ? new ResponseEntity<>(carte, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{numeroCarte}")
    public ResponseEntity<Void> supprimerCarte(@PathVariable Long numeroCarte) {
        try {
            carteService.supprimerCarte(numeroCarte);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/compte/{iban}")
    public ResponseEntity<List<Carte>> listerCartesDuCompte(@PathVariable String iban) {
        try {
            List<Carte> cartes = carteService.listerCartesDuCompte(iban);
            return new ResponseEntity<>(cartes, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/paiements/{numeroCarte}")
    public ResponseEntity<List<Paiement>> listerPaiementsDeLaCarte(@PathVariable Long numeroCarte) {
        try {
            List<Paiement> paiements = carteService.listerPaiementsDeLaCarte(numeroCarte);
            return new ResponseEntity<>(paiements, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
