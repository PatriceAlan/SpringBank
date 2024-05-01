package com.project.SpringBank.services;

import com.project.SpringBank.DTO.carte.CreateCarteDTO;
import com.project.SpringBank.DTO.carte.ResponseCarteDTO;
import com.project.SpringBank.entities.Carte;
import com.project.SpringBank.repositories.CarteRepository;
import jakarta.transaction.Transactional;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Builder
public class CarteService {

    private final CarteRepository carteRepository;

    @Transactional
    public Carte createCarte(CreateCarteDTO carteDTO){
        Carte carte = Carte.builder()
                .numeroCarte(carteDTO.getNumeroCarte())
                .dateExpiration(carteDTO.getDateExpiration())
                .codeSecurite(carteDTO.getCodeSecurite())
                .build();
        return carteRepository.save(carte);
    }

    public List<ResponseCarteDTO> listCartes(){
        List<Carte> cartes = carteRepository.findAll();
        return cartes.stream()
                .map(this::mapCarteToResponseDTO)
                .toList();
    }

    public ResponseCarteDTO mapCarteToResponseDTO(Carte carte){
        return ResponseCarteDTO.builder()
                .numeroCarte(carte.getNumeroCarte())
                .dateExpiration(carte.getDateExpiration())
                .titulaireCarte(carte.getTitulaireCarte())
                .build();
    }

}
