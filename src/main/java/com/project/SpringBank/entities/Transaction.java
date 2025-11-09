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
    @Column(name = "id", nullable = false, updatable = false)
    private Long idTransaction;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_transaction", nullable = false)
    private TypeTransaction typeTransaction;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_source", nullable = false)
    private TypeSource typeSource;

    @Column(name = "libelle_transaction")
    private String libelleTransaction;

    @Column(name = "date_transaction", nullable = false)
    private LocalDateTime dateTransaction;

    @Column(name = "montant_transaction", nullable = false)
    private double montantTransaction;

    @ManyToOne
    @JoinColumn(name = "compte_id", nullable = false)
    private Compte compte;

}

