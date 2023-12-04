package com.project.SpringBank.entities;

import java.sql.Date;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idTransaction;

    @Enumerated(EnumType.STRING)
    private TypeTransaction typeTransaction;

    @Enumerated(EnumType.STRING)
    private TypeSource typeSource;

    private Date dateTransaction;

    private double montant;

    @ManyToOne
    @JoinColumn(name = "iban")
    private Compte compte;
    
}

