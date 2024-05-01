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
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPaiement;

    @ManyToOne
    @JoinColumn(name = "numeroCarte")
    private Carte carte;

    private int codeSecurite;

    private double montantPaiement;

    private LocalDateTime datePaiement;

    private Long idTransaction;

    
}
