package com.project.SpringBank.entities;

import java.sql.Date;
import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

@Getter
@Setter
@Entity
public class Carte {

    @Id
    private Long numeroCarte;
    
    private int codeSecurite;

    private Date dateExpiration;

    @ManyToOne(targetEntity = Client.class)
    private Client titulaireCarte;

    @ManyToOne(targetEntity = Compte.class)
    @JoinColumn(name = "iban")
    private Compte compteAssocie;

    @OneToMany(mappedBy = "carte")
    private List<Paiement> paiements;
    
    
}
