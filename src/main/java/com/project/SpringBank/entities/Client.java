package com.project.SpringBank.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idClient;

    @NotBlank
    @NotEmpty
    @NotNull
    @Column(length = 100)
    private String prenom;

    @NotBlank
    @NotEmpty
    @NotNull
    @Column(length = 100, nullable = false)
    private String nom;

    @NotNull
    @Past
    @Temporal(TemporalType.DATE)
    private LocalDate dateNaissance;

    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    @NotBlank
    @NotEmpty
    @NotNull
    @Column(length = 10, nullable = false)
    private String numeroTelephone;

    @Column(nullable = false)
    private String adressePostale;

    @PastOrPresent
    @Temporal(TemporalType.DATE)
    private LocalDate dateCreation;


    @ManyToOne
    @JoinColumn
    private Carte carte;

    @ToString.Exclude
    @ManyToMany(mappedBy = "clients")
    private List<Compte> comptes = new ArrayList<>();
}
