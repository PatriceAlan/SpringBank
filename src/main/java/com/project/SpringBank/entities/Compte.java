package com.project.SpringBank.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;

@Entity
@Getter
@Setter
public class Compte {

    private String iban;

    private Long numero_compte;

    @ManyToMany
    private Client titulaire_compte;

    private Long solde;

    @Column(length = 100, nullable = false)
    private String intitule_compte;

    private Date date_creation;

    
}
