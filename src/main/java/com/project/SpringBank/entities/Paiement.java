package com.project.SpringBank.entities;
import lombok.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
@Getter
@Setter
public class Paiement{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_paiement;

    @ManyToOne
    @JoinColumn(name = "numero_carte")
    private Carte carte;

    @OneToOne
    @JoinColumn(name = "id_transaction")
    private Transaction transaction;

    private int code_securite;
    
}
