package com.project.SpringBank.services.transaction;

import com.project.SpringBank.entities.Virement;
import com.project.SpringBank.repositories.TransactionRepository;

public class TransactionService {

    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


}
