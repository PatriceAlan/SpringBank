package com.project.SpringBank.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idClient;

    @Column(length = 100)
    private String prenom;

    @Column(length = 100, nullable = false)
    private String nom;

    @Temporal(TemporalType.DATE)
    private LocalDate dateNaissance;

    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    @Column(length = 10, nullable = false)
    private String numeroTelephone;

    @Column(nullable = false)
    private String adressePostale;

    @Temporal(TemporalType.DATE)
    private LocalDate dateCreation;

    @ManyToOne(targetEntity = Carte.class)
    private Carte carte;

    @OneToMany(mappedBy = "titulaireCompte", cascade = CascadeType.ALL)
    private List<Compte> comptes;
}
