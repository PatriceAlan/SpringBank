package com.project.SpringBank.controllers;

import com.project.SpringBank.DTO.carte.CreateCarteDTO;
import com.project.SpringBank.DTO.carte.ResponseCarteDTO;
import com.project.SpringBank.entities.Carte;
import com.project.SpringBank.services.CarteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartes")
public class CarteController {

    private final CarteService carteService;

    public  CarteController(CarteService carteService) {
        this.carteService = carteService;
    }

    @PostMapping
    public ResponseEntity<ResponseCarteDTO> createCarte(@RequestBody CreateCarteDTO carteDTO){
        ResponseCarteDTO createdCarte = mapCarteToResponseDTO(carteService.createCarte(carteDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCarte);
    }

    public ResponseCarteDTO mapCarteToResponseDTO(Carte carte){
        return ResponseCarteDTO.builder()
                .numeroCarte(carte.getNumeroCarte())
                .dateExpiration(carte.getDateExpiration())
                .titulaireCarte(carte.getTitulaireCarte())
                .build();
    }

    @GetMapping
    public ResponseEntity<List<ResponseCarteDTO>> listCartes(){
        List<ResponseCarteDTO> cartes = carteService.listCartes();
        return ResponseEntity.ok(cartes);
    }

    @DeleteMapping("/{numeroCarte}")
    public ResponseEntity<Void> deleteCarte(@PathVariable Long numeroCarte){
        carteService.deleteCarte(numeroCarte);
        return ResponseEntity.noContent().build();
    }


}
