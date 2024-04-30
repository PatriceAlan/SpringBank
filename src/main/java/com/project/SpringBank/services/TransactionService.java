package com.project.SpringBank.services;

import com.project.SpringBank.entities.Transaction;
import com.project.SpringBank.entities.TypeSource;
import com.project.SpringBank.repositories.TransactionRepository;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Builder
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public Transaction sauvegarderOuMettreAJourTransaction(Transaction transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("La transaction ne peut pas être nulle");
        }

        return this.transactionRepository.save(Transaction.builder()
                .idTransaction(transaction.getIdTransaction())
                .montant(transaction.getMontant())
                .dateTransaction(transaction.getDateTransaction())
                .build());
    }

    public Transaction rechercherTransaction(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID ne peut pas être nul");
        }

        return this.transactionRepository.findById(id).orElse(null);
    }

    public void supprimerTransaction(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID ne peut pas être nul");
        }

        this.transactionRepository.deleteById(id);
    }

    public List<Transaction> listerTransactionsDuCompte(String iban) {
        if (iban == null || iban.isEmpty()) {
            throw new IllegalArgumentException("L'iban ne peut pas être nul ou vide");
        }

        return this.transactionRepository.findAllByCompteAssocie_Iban(iban);
    }

    public List<Transaction> listerTransactionsParTypeSource(TypeSource typeSource) {
        if (typeSource == null) {
            throw new IllegalArgumentException("Le type de source ne peut pas être nul");
        }

        return this.transactionRepository.findAllByTypeSource(typeSource);
    }

}
