package com.project.SpringBank.controllers;

import com.project.SpringBank.entities.Transaction;
import com.project.SpringBank.entities.TypeSource;
import com.project.SpringBank.services.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/ajouterTransaction")
    public ResponseEntity<Transaction> ajouterOuMettreAJourTransaction(@RequestBody Transaction transaction) {
        try {
            Transaction savedTransaction = transactionService.sauvegarderOuMettreAJourTransaction(transaction);
            return new ResponseEntity<>(savedTransaction, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> rechercherTransactionParId(@PathVariable Long id) {
        try {
            Transaction transaction = transactionService.rechercherTransaction(id);
            return transaction != null
                    ? new ResponseEntity<>(transaction, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerTransaction(@PathVariable Long id) {
        try {
            transactionService.supprimerTransaction(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/compte/{iban}")
    public ResponseEntity<List<Transaction>> listerTransactionsDuCompte(@PathVariable String iban) {
        try {
            List<Transaction> transactions = transactionService.listerTransactionsDuCompte(iban);
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/type-source/{typeSource}")
    public ResponseEntity<List<Transaction>> listerTransactionsParTypeSource(@PathVariable TypeSource typeSource) {
        try {
            List<Transaction> transactions = transactionService.listerTransactionsParTypeSource(typeSource);
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
