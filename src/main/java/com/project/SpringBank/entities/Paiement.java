package com.project.SpringBank.entities;
import lombok.*;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Paiement extends Transaction{

    @ManyToOne
    private Carte numero_carte;
    
    private int code_securite;
    
}
