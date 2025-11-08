package com.project.SpringBank.entities;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private int codeSecurite;

    private double montantPaiement;

    private LocalDateTime datePaiement;

    private Long idTransaction;


    @ManyToOne
    @JoinColumn(name = "carte_numero_carte")
    private Carte carte;

}
