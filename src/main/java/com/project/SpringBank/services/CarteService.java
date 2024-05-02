package com.project.SpringBank.services;

import com.project.SpringBank.DTO.carte.CreateCarteDTO;
import com.project.SpringBank.DTO.carte.ResponseCarteDTO;
import com.project.SpringBank.entities.Carte;
import com.project.SpringBank.entities.Client;
import com.project.SpringBank.entities.Compte;
import com.project.SpringBank.repositories.CarteRepository;
import com.project.SpringBank.repositories.ClientRepository;
import com.project.SpringBank.repositories.CompteRepository;
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
    private final CompteRepository compteRepository;

    @Transactional
    public Carte createCarte(CreateCarteDTO carteDTO, String iban){

        Optional<Client> clientOptional = clientRepository.findById(carteDTO.getTitulaireCarte());
        Optional<Compte> compteOptional = compteRepository.findByIban(iban);

        if (compteOptional.isEmpty()) {

            throw new EntityNotFoundException("Compte non trouvé avec l'iban : " + carteDTO.getCompteAssocie());
        }

        if (clientOptional.isEmpty()) {

            throw new EntityNotFoundException("Client non trouvé avec l'ID : " + carteDTO.getTitulaireCarte());
        }

        // Obtenir les objets client et compte à partir de l'Optional
        Client client = clientOptional.get();
        Compte compte = compteOptional.get();

        Carte carte = Carte.builder()
                .numeroCarte(carteDTO.getNumeroCarte())
                .dateExpiration(carteDTO.getDateExpiration())
                .codeSecurite(carteDTO.getCodeSecurite())
                .titulaireCarte(client)
                .compteAssocie(compte)
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
