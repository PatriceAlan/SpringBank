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
public class PaiementCarte {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_paiement", nullable = false, updatable = false)
    private Long idPaiement;

    @Column(name = "date_paiement", nullable = false)
    private LocalDateTime datePaiement;

    @Column(name = "libelle_paiement", nullable = false)
    private String libellePaiementCarte;

    @Column(name = "montant_paiement", nullable = false)
    private double montantPaiementCarte;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_id", unique = true)
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "carte_id")
    private Carte carte;

}
