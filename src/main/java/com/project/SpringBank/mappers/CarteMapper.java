package com.project.SpringBank.mappers;

import com.project.SpringBank.DTO.carte.CreateCarteResponseDTO;
import com.project.SpringBank.DTO.carte.GetCarteResponseDTO;
import com.project.SpringBank.entities.Carte;
import org.springframework.stereotype.Component;

import static com.project.SpringBank.Utils.FonctionsGeneratives.genererNumeroCompte;

@Component
public class CarteMapper {


    public GetCarteResponseDTO toGetCarteResponseDTO(Carte carte){
        return GetCarteResponseDTO.builder()
                .numeroCarte(carte.getNumeroCarte())
                .dateExpiration(carte.getDateExpiration())
                .titulaireCarte(carte.getTitulaireCarte().getIdClient())
                .build();
    }

    public CreateCarteResponseDTO toCreateCarteResponseDTO(Carte carte){
        return CreateCarteResponseDTO.builder()
                .titulaireCarte(carte.getTitulaireCarte().getIdClient())
                .numeroCarte(genererNumeroCompte())
                .dateExpiration(carte.getDateExpiration())
                .build();
    }
}
