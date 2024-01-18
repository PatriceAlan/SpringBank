package com.project.SpringBank.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Carte {

    @Id
    private Long numeroCarte;
    
    private int codeSecurite;

    private LocalDate dateExpiration;

    @ManyToOne
    private Client titulaireCarte;

    @ManyToOne
    @JoinColumn(name = "iban")
    private Compte compteAssocie;

    @ToString.Exclude
    @OneToMany(mappedBy = "carte")
    private List<Paiement> paiements;

}
