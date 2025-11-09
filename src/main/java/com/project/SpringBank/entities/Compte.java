package com.project.SpringBank.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.SpringBank.Utils.BanqueConstantes;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Compte {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "iban", unique = true, nullable = false)
    private String iban;

    @Column(name = "numero_compte", unique = true, nullable = false)
    private Long numeroCompte;

    @Column(name = "code_agence", nullable = false)
    public static final String codeAgence = BanqueConstantes.CODE_AGENCE;

    @Column(name = "code_guichet", nullable = false)
    public static final String codeGuichet = BanqueConstantes.CODE_GUICHET;

    @Column(name = "solde")
    private double solde;

    @Column(name = "type_compte", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeCompte typeCompte;

    @Column(name = "intitule_compte", length = 100, nullable = false)
    private String intituleCompte;

    @Column(name = "date_creation", nullable = false)
    private LocalDateTime dateCreation;

    @ManyToMany
    @JoinTable(name = "titulairesCompte",
    joinColumns = @JoinColumn(name = "compte_id"),
    inverseJoinColumns = @JoinColumn(name = "client_id"))
    private Set<Client> titulaires;

    @OneToMany(mappedBy = "compte")
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "compteAssocie")
    @JsonIgnore
    private List<Carte> cartes;


}
