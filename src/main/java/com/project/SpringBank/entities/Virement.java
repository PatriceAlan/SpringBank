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
@Builder
public class Virement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idVirement;

    @ManyToOne
    @JoinColumn(name = "ibanCompteEmetteur")
    private Compte compteEmetteur;

    @ManyToOne
    @JoinColumn(name = "ibanCompteBeneficiaire")
    private Compte compteBeneficiaire;

    private String libelleVirement;

    private double montantVirement;

    private LocalDate dateVirement;
}
