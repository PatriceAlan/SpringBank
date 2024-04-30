package com.project.SpringBank.controllers;

import com.project.SpringBank.entities.Virement;
import com.project.SpringBank.services.VirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/virements")
public class VirementController {

    private final VirementService virementService;

    @Autowired
    public VirementController(VirementService virementService) {
        this.virementService = virementService;
    }

    @PostMapping
    public ResponseEntity<Virement> ajouterOuMettreAJourVirement(@RequestBody Virement virement) {
        try {
            Virement savedVirement = virementService.sauvegarderOuMettreAJourVirement(virement);
            return new ResponseEntity<>(savedVirement, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Virement> rechercherVirementParId(@PathVariable Long id) {
        try {
            Virement virement = virementService.rechercherVirement(id);
            return virement != null
                    ? new ResponseEntity<>(virement, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/virements-emis/{iban}")
    public ResponseEntity<List<Virement>> listerVirementsEmisDuCompte(@PathVariable String iban) {
        try {
            List<Virement> virementsEmis = virementService.listerVirementsEmisDuCompte(iban);
            return new ResponseEntity<>(virementsEmis, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/virements-recus/{iban}")
    public ResponseEntity<List<Virement>> listerVirementsRecusDuCompte(@PathVariable String iban) {
        try {
            List<Virement> virementsRecus = virementService.listerVirementsRecusSurLeCompte(iban);
            return new ResponseEntity<>(virementsRecus, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/tous-les-virements-du-compte/{iban}")
    public ResponseEntity<List<Virement>> listerTousLesVirementsDuCompte(@PathVariable String iban) {
        try {
            List<Virement> tousLesVirements = virementService.listerTousLesVirementsDuCompte(iban);
            return new ResponseEntity<>(tousLesVirements, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
