package com.project.SpringBank.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Virement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idVirement;

    @ManyToOne
    @JoinColumn(name = "ibanCompteCrediteur")
    private Compte compteCrediteur;

    @ManyToOne
    @JoinColumn(name = "ibanCompteDebiteur")
    private Compte compteDebiteur;

    private String libelleVirement;

    private double montantVirement;

    private LocalDate dateVirement;
}
