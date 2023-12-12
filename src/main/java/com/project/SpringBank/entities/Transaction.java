package com.project.SpringBank.entities;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTransaction;

    @Enumerated(EnumType.STRING)
    private TypeTransaction typeTransaction;

    @Enumerated(EnumType.STRING)
    private TypeSource typeSource;

    private Long idSource;

    private LocalDate dateTransaction;

    private double montant;

    @ManyToOne
    @JoinColumn(name = "iban")
    private Compte compte;


}

