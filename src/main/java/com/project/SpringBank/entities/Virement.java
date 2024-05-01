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
    private Long idVirement;

    @Column(name = "ibanCompteEmetteur")
    private String ibanCompteEmetteur;

    @Column(name = "ibanCompteBeneficiaire")
    private String ibanCompteBeneficiaire;

    private String libelleVirement;

    private double montantVirement;

    private LocalDateTime dateVirement;

    @OneToOne
    private Transaction transaction;

}
