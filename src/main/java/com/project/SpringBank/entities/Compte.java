package com.project.SpringBank.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class Compte {

    @Id
    private String iban;

    private Long numeroCompte;

    private double solde;

    private int cleRIB;

    @Enumerated(EnumType.STRING)
    private TypeCompte typeCompte;


    @JsonIgnore
    @ToString.Exclude
    @JsonManagedReference
    @ManyToMany
    @JoinTable(name = "clientCompte", joinColumns = @JoinColumn(name = "iban"),
    inverseJoinColumns = @JoinColumn(name = "idClient"))
    private Set<Client> titulaireCompte;

    @Column(length = 100, nullable = false)
    private String intituleCompte;

    private LocalDateTime dateCreation;

    public Compte() {
        this.titulaireCompte = new HashSet<>();
    }

    @ToString.Exclude
    @OneToMany(mappedBy = "compteAssocie", cascade = CascadeType.ALL)
    private List<Transaction> transactions;


    @ToString.Exclude
    @OneToMany(mappedBy = "compteAssocie", cascade = CascadeType.ALL)
    private List<Carte> cartes;


}
