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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVirement;

    @OneToOne
    @JoinColumn(name = "idTransaction")
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "ibanCompteDebiteur")
    private Compte ibanCompteDebiteur;

    @ManyToOne
    @JoinColumn(name = "ibanCompteCrediteur")
    private Compte ibanCompteCrediteur;
    
    private String libelleVirement;

    @ManyToOne
    @JoinColumn(name = "compteDebiteurIban")
    private Compte compteDebiteur;

    @ManyToOne
    @JoinColumn(name = "compteCrediteurIban")
    private Compte compteCrediteur;

}
