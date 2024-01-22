package com.project.SpringBank.controllers;

import com.project.SpringBank.entities.Compte;
import com.project.SpringBank.entities.Transaction;
import com.project.SpringBank.entities.TypeCompte;
import com.project.SpringBank.entities.Virement;
import com.project.SpringBank.services.compte.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comptes")
public class CompteController {

    private final CompteService compteService;

    @Autowired
    public CompteController(CompteService compteService) {
        this.compteService = compteService;
    }

    @PostMapping("/creer-compte")
    public ResponseEntity<Compte> creerCompte(@RequestBody Compte compte, @RequestParam List<Long> titulaireIds) {
        try {
            Compte createdCompte = compteService.creerCompte(compte, titulaireIds);
            return new ResponseEntity<>(createdCompte, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/mettre-a-jour-compte")
    public ResponseEntity<Compte> mettreAJourCompte(@RequestBody Compte compte, @RequestParam List<Long> titulairesIds) {
        try {
            Compte updatedCompte = compteService.MettreAJourCompte(compte, titulairesIds);
            return new ResponseEntity<>(updatedCompte, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{iban}")
    public ResponseEntity<Compte> rechercherCompteParIban(@PathVariable String iban) {
        try {
            Optional<Compte> compte = compteService.rechercherCompte(iban);
            return compte.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/tous-les-comptes")
    public ResponseEntity<List<Compte>> listerTousLesComptes() {
        List<Compte> comptes = compteService.listerTousLesComptes();
        return new ResponseEntity<>(comptes, HttpStatus.OK);
    }

    @GetMapping("/type/{typeCompte}")
    public ResponseEntity<List<Compte>> listerComptesParType(@PathVariable TypeCompte typeCompte) {
        List<Compte> comptes = compteService.trouverComptesParType(typeCompte);
        return new ResponseEntity<>(comptes, HttpStatus.OK);
    }

    @DeleteMapping("/{iban}")
    public ResponseEntity<Void> supprimerCompte(@PathVariable String iban) {
        try {
            compteService.supprimerCompte(iban);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/transactions/{iban}")
    public ResponseEntity<List<Transaction>> listerTransactionsDuCompte(@PathVariable String iban) {
        try {
            List<Transaction> transactions = compteService.listerTransactionsDuCompte(iban);
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/virements-emis/{iban}")
    public ResponseEntity<List<Virement>> listerVirementsEmisDuCompte(@PathVariable String iban) {
        try {
            List<Virement> virementsEmis = compteService.listerVirementsEmisDuCompte(iban);
            return new ResponseEntity<>(virementsEmis, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/virements-recus/{iban}")
    public ResponseEntity<List<Virement>> listerVirementsRecusDuCompte(@PathVariable String iban) {
        try {
            List<Virement> virementsRecus = compteService.listerVirementsRecusDuCompte(iban);
            return new ResponseEntity<>(virementsRecus, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
