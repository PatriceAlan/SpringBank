package com.project.SpringBank.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToMany(mappedBy = "carte")
    private List<Paiement> paiements;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "numeroCarte = " + numeroCarte + ", " +
                "codeSecurite = " + codeSecurite + ", " +
                "dateExpiration = " + dateExpiration + ", " +
                "titulaireCarte = " + titulaireCarte + ", " +
                "compteAssocie = " + compteAssocie + ")";
    }
}
