package com.project.SpringBank.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTransaction;

    @Enumerated(EnumType.STRING)
    private TypeTransaction typeTransaction;

    @Enumerated(EnumType.STRING)
    private TypeSource typeSource;

    private LocalDate dateTransaction;

    private double montant;

    @ManyToOne
    @JoinColumn(name = "iban")
    private Compte compte;

    @OneToOne(mappedBy = "transaction", cascade = CascadeType.ALL)
    private Virement virement;

    @OneToOne(mappedBy = "transaction", cascade = CascadeType.ALL)
    private Paiement paiement;
    
}

