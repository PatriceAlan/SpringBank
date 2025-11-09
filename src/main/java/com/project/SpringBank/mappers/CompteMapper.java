package com.project.SpringBank.mappers;

import com.project.SpringBank.DTO.compte.CreateCompteResponseDTO;
import com.project.SpringBank.DTO.compte.GetComptesResponseDTO;
import com.project.SpringBank.DTO.transaction.GetTransactionsCompteResponseDTO;
import com.project.SpringBank.entities.Client;
import com.project.SpringBank.entities.Compte;
import com.project.SpringBank.entities.Transaction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CompteMapper {

    public CreateCompteResponseDTO createCompteResponseDTO(Compte compte){
        Set<Long> titulaires = new HashSet<>();
        for (Client client : compte.getTitulairesCompte()) {
            titulaires.add(client.getIdClient());
        }
        return CreateCompteResponseDTO.builder()
                .iban(compte.getIban())
                .solde(compte.getSolde())
                .typeCompte(compte.getTypeCompte())
                .intituleCompte(compte.getIntituleCompte())
                .dateCreation(compte.getDateCreation())
                .titulairesCompte(titulaires)
                .build();
    }

    public GetComptesResponseDTO toGetComptesResponseDTO(Compte compte){

        Set<Long> titulaires = new HashSet<>();
        for (Client client : compte.getTitulairesCompte()) {
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
                .titulairesCompte(titulaires)
                .intituleCompte(compte.getIntituleCompte())
                .transactions(transactionsDTO)
                .build();

    }

}
