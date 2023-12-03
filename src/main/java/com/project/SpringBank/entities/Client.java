package com.project.SpringBank.entities;

import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.*;

@Entity
@Getter
@Setter
public class Client {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100)
    private String prenom;

    @Column(length = 100, nullable = false)
    private String nom;

    private Date date_naissance;

    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    private int numero_telephone;

    @Column(length = 255, nullable = false)
    private String adresse_postale;

    private Date date_creation;

    @OneToMany(mappedBy = "titulaire_compte")
    private Carte carte;
    
    
}
