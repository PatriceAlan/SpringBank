package com.project.SpringBank.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Entity
@Getter
@Setter
public class Compte {

    @Id
    private String iban;

    private Long numeroCompte;

    private double solde;

    @Enumerated(EnumType.STRING)
    private TypeCompte typeCompte;

    @ManyToMany
    @JoinTable(name = "clientCompte", joinColumns = @JoinColumn(name = "iban"),
    inverseJoinColumns = @JoinColumn(name = "idClient"))
    private Set<Client> clients = new HashSet<>();

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
