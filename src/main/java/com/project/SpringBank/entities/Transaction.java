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

    private String libelle;

    private LocalDateTime dateTransaction;

    private double montantTransaction;

    private String ibanContrepartie;

    @ManyToOne
    @JoinColumn(name = "compte_id", nullable = false)
    private Compte compte;


}

