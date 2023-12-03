package com.project.SpringBank.entities;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
public class Virement extends Transaction {

    private Compte iban_compte_debiteur;
    private Compte iban_compte_crediteur;
    private String libelle;
    
}
