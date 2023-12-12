package com.project.SpringBank.entities;
import lombok.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paiement{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPaiement;

    @ManyToOne
    @JoinColumn(name = "numeroCarte")
    private Carte carte;

    private int codeSecurite;

    private double montantPaiement;

    private LocalDate datePaiement;
    
}
