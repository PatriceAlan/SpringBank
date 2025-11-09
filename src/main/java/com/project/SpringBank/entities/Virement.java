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
    @Column(name = "id_virement", nullable = false, updatable = false)
    private Long idVirement;

    @Column(name = "iban_compte_emetteur", nullable = false)
    private String ibanCompteEmetteur;

    @Column(name = "iban_compte_receveur", nullable = false)
    private String ibanCompteReceveur;

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
