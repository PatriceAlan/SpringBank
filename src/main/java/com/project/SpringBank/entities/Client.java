package com.project.SpringBank.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne
    private Carte carte;

    @ManyToMany(mappedBy = "clients")
    private List<Compte> comptes = new ArrayList<>();
}
