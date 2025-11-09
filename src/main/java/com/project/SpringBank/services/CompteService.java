package com.project.SpringBank.services;

import com.project.SpringBank.DTO.compte.CreateCompteRequestDTO;
import com.project.SpringBank.DTO.compte.CreateCompteResponseDTO;
import com.project.SpringBank.DTO.compte.GetComptesResponseDTO;
import com.project.SpringBank.DTO.transaction.GetTransactionsCompteResponseDTO;
import com.project.SpringBank.entities.Client;
import com.project.SpringBank.entities.Compte;
import com.project.SpringBank.entities.Transaction;
import com.project.SpringBank.repositories.ClientRepository;
import com.project.SpringBank.repositories.CompteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

import static com.project.SpringBank.Utils.FonctionsGeneratives.genererIban;
import static com.project.SpringBank.Utils.FonctionsGeneratives.genererNumeroCompte;

@Service
@RequiredArgsConstructor
public class CompteService {

    private final CompteRepository compteRepository;
    private final ClientRepository clientRepository;


    public Compte createCompte(CreateCompteRequestDTO compteCree) {

        // Recherche des clients par leur ID
        Set<Client> titulaires = new HashSet<>();
        for (Long clientId : compteCree.getTitulaireCompte()) {
            Optional<Client> existingClient = clientRepository.getClientById(clientId);
            if (existingClient.isEmpty()){
                throw new IllegalArgumentException("Le client avec l'ID " + clientId + "est introuvable.");
            }
            titulaires.add(existingClient.get());
        }

        Long numeroCompteCree = genererNumeroCompte();
        String ibanCree = genererIban(numeroCompteCree);

        Compte compte = Compte.builder()
                .typeCompte(compteCree.getTypeCompte())
                .numeroCompte(numeroCompteCree)
                .titulaires(titulaires)
                .intituleCompte(compteCree.getIntituleCompte())
                .dateCreation(LocalDateTime.now())
                .iban(ibanCree)
                .build();

        return compteRepository.save(compte);
    }



    public CreateCompteResponseDTO mapCompteToResponseDTO(Compte compte){

        Set<Long> titulaires = new HashSet<>();
        for (Client client : compte.getTitulaires()) {
            titulaires.add(client.getIdClient());
        }
        return CreateCompteResponseDTO.builder()
                .iban(compte.getIban())
                .typeCompte(compte.getTypeCompte())
                .titulaireCompte(titulaires)
                .intituleCompte(compte.getIntituleCompte())
                .dateCreation(compte.getDateCreation())
                .build();
    }

    public GetComptesResponseDTO mapGetComptesToResponseDTO(Compte compte){

        Set<Long> titulaires = new HashSet<>();
        for (Client client : compte.getTitulaires()) {
            titulaires.add(client.getIdClient());
        }

        List<GetTransactionsCompteResponseDTO> transactionsDTO = new ArrayList<>();
        if (compte.getTransactions() != null) {
            for (Transaction transaction : compte.getTransactions()) {
                GetTransactionsCompteResponseDTO transactionDTO = GetTransactionsCompteResponseDTO.builder()
                        .idTransaction(transaction.getIdTransaction())
                        .typeTransaction(transaction.getTypeTransaction())
                        .typeSource(transaction.getTypeSource())
                        .montantTransaction(transaction.getMontantTransaction())
                        .dateTransaction(transaction.getDateTransaction())
                        .build();
                transactionsDTO.add(transactionDTO);
            }
        }

        return GetComptesResponseDTO.builder()
                .iban(compte.getIban())
                .solde(compte.getSolde())
                .typeCompte(compte.getTypeCompte())
                .titulaireCompte(titulaires)
                .intituleCompte(compte.getIntituleCompte())
                .transactions(transactionsDTO)
                .build();

    }

    public List<GetComptesResponseDTO> listComptes(){
        List<Compte> comptes = compteRepository.findAll();
        return comptes.stream()
                .map(this::mapGetComptesToResponseDTO)
                .toList();
    }



}
