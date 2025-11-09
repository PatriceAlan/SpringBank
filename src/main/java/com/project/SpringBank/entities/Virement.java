package com.project.SpringBank.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Virement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long idVirement;

    @Column(name = "iban_compte_debiteur", nullable = false)
    private String ibanCompteDebiteur;

    @Column(name = "iban_compte_crediteur", nullable = false)
    private String ibanCompteCrediteur;

    @Column(name = "date_virement", nullable = false)
    private LocalDateTime dateVirement;

    @Column(name = "libelle_virement")
    private String libelleVirement;

    @Column(name = "montant_virement", nullable = false)
    private double montantVirement;

    @OneToOne
    @JoinColumn(name = "transaction_id", unique = true)
    private Transaction transaction;

}
