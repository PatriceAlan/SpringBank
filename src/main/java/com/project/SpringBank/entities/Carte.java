package com.project.SpringBank.entities;

import java.sql.Date;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

@Getter
@Setter
public class Carte {

    private long numero_carte;
    
    private int code_securite;

    private Date date_expiration;

    @ManyToOne
    private Client titulaire_carte;

    @OneToMany(mappedBy = "numero_carte")
    private Paiement paiement;
    
    
}
