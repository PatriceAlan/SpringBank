package com.project.SpringBank.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String iban;

    private Long numeroCompte;

    private double solde;

    @Enumerated(EnumType.STRING)
    private TypeCompte typeCompte;

    @ManyToOne(targetEntity = Client.class)
    @JoinColumn(name = "idClient")
    private Client titulaireCompte;

    @Column(length = 100, nullable = false)
    private String intituleCompte;

    private LocalDate dateCreation;

    @OneToMany(mappedBy = "compte", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "compteCrediteur", cascade = CascadeType.ALL)
    private List<Virement> virementsEmis;

    @OneToMany(mappedBy = "compteDebiteur", cascade = CascadeType.ALL)
    private List<Virement> virementsRecus;

    @OneToMany(mappedBy = "compteAssocie", cascade = CascadeType.ALL)
    private List<Carte> cartes;


}
