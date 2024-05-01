package com.project.SpringBank.services;

import com.project.SpringBank.DTO.carte.CreateCarteDTO;
import com.project.SpringBank.DTO.carte.ResponseCarteDTO;
import com.project.SpringBank.entities.Carte;
import com.project.SpringBank.entities.Client;
import com.project.SpringBank.repositories.CarteRepository;
import com.project.SpringBank.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Builder
public class CarteService {

    private final CarteRepository carteRepository;
    private final ClientRepository clientRepository;

    @Transactional
    public Carte createCarte(CreateCarteDTO carteDTO){

        Optional<Client> clientOptional = clientRepository.findById(carteDTO.getTitulaireCarte());

        if (clientOptional.isEmpty()) {

            throw new EntityNotFoundException("Client non trouvé avec l'ID : " + carteDTO.getTitulaireCarte());
        }

        // Obtenir l'objet Client à partir de l'Optional
        Client client = clientOptional.get();

        Carte carte = Carte.builder()
                .numeroCarte(carteDTO.getNumeroCarte())
                .dateExpiration(carteDTO.getDateExpiration())
                .codeSecurite(carteDTO.getCodeSecurite())
                .titulaireCarte(client)
                .build();

        return carteRepository.save(carte);
    }



    public ResponseCarteDTO mapCarteToResponseDTO(Carte carte){
        Long idTitulaire = carte.getTitulaireCarte().getIdClient();

        return ResponseCarteDTO.builder()
                .numeroCarte(carte.getNumeroCarte())
                .dateExpiration(carte.getDateExpiration())
                .titulaireCarte(idTitulaire)
                .build();
    }

}
