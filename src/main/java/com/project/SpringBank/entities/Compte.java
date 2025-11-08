package com.project.SpringBank.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String iban;

    private Long numeroCompte;

    private double solde;

    private int cleRIB;

    @Enumerated(EnumType.STRING)
    private TypeCompte typeCompte;


    @ManyToMany
    @JoinTable(name = "clientCompte",
    joinColumns = @JoinColumn(name = "compte_id"),
    inverseJoinColumns = @JoinColumn(name = "client_id"))
    private Set<Client> titulaires;

    @Column(length = 100, nullable = false)
    private String intituleCompte;

    private LocalDateTime dateCreation;

    public Compte() {
        this.titulaires = new HashSet<>();
    }

    @OneToMany(mappedBy = "compte")
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "compteAssocie")
    @JsonIgnore
    private List<Carte> cartes;


}
