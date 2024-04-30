package com.project.SpringBank.controllers;

import com.project.SpringBank.DTO.compte.CreateCompteDTO;
import com.project.SpringBank.DTO.compte.ResponseCompteDTO;
import com.project.SpringBank.entities.Compte;
import com.project.SpringBank.services.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comptes")
public class CompteController {

    private final CompteService compteService;

    @Autowired
    public CompteController(CompteService compteService) {
        this.compteService = compteService;
    }

    @PostMapping
    public ResponseEntity<ResponseCompteDTO> createCompte(@RequestBody CreateCompteDTO compteDTO) {
        ResponseCompteDTO createdCompte = compteService.mapCompteToResponseDTO(compteService.createCompte(compteDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCompte);
    }

    @GetMapping
    public ResponseEntity<List<ResponseCompteDTO>> listComptes() {
        List<ResponseCompteDTO> comptes = compteService.listComptes();
        return ResponseEntity.ok(comptes);
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

}
