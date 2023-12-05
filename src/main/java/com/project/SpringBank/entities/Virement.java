package com.project.SpringBank.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity
@Getter
@Setter
public class Virement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idVirement;

    @OneToOne
    @JoinColumn(name = "idTransaction")
    private Transaction transaction;

    
    private String libelleVirement;


    @ManyToOne
    @JoinColumn(name = "ibanCompteCrediteur")
    private Compte compteCrediteur;

    @ManyToOne
    @JoinColumn(name = "ibanCompteDebiteur")
    private Compte compteDebiteur;

}
