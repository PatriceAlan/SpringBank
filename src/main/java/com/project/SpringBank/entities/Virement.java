package com.project.SpringBank.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
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
