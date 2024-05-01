package com.project.SpringBank.entities;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTransaction;

    @Enumerated(EnumType.STRING)
    private TypeTransaction typeTransaction;

    @Enumerated(EnumType.STRING)
    private TypeSource typeSource;


    private LocalDateTime dateTransaction;

    private double montantTransaction;

    @ManyToOne
    @JoinColumn(name = "iban")
    private Compte compteTransactions;


}

