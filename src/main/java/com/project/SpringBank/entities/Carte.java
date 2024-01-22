package com.project.SpringBank.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonIgnore
    @JsonManagedReference
    private Client titulaireCarte;

    @ManyToOne
    @JoinColumn(name = "iban")
    private Compte compteAssocie;

    @ToString.Exclude
    @OneToMany(mappedBy = "carte")
    @JsonIgnore
    @JsonManagedReference
    private List<Paiement> paiements;

}
