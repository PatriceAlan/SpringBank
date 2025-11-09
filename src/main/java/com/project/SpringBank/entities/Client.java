package com.project.SpringBank.entities;

import com.project.SpringBank.Utils.BanqueConstantes;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_client", nullable = false, updatable = false)
    private Long idClient;

    @Column(name = "prenom", length = 100)
    private String prenom;

    @Column(name = "nom", length = 100, nullable = false)
    private String nom;

    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissance;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(length = 10, nullable = false)
    private String numeroTelephone;

    @Column(name = "adresse_postale", nullable = false)
    private String adressePostale;

    @Column(name = "code_banque", nullable = false)
    public static final String codeAgence = BanqueConstantes.CODE_AGENCE;

    @Column(name = "code_guichet", nullable = false)
    public static final String codeGuichet = BanqueConstantes.CODE_GUICHET;

    @Column(name = "date_creation", nullable = false)
    private LocalDateTime dateCreation;

    @Column(name = "date_modification")
    private LocalDateTime dateModification;

    @OneToMany(mappedBy = "titulaireCarte")
    private List<Carte> cartes = new ArrayList<>();

    @ManyToMany(mappedBy = "titulairesCompte")
    private Set<Compte> comptes = new HashSet<>();
}
