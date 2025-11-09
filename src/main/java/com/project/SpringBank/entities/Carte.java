package com.project.SpringBank.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "cartes")
public class Carte {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_carte", unique = true)
    private Long numeroCarte;

    @Column(name = "date_expiration")
    private LocalDateTime dateExpiration;

    @Column(name = "active")
    private boolean active;

    @Column(name = "code_securite")
    private int codeSecurite;

    @ManyToOne
    @JoinColumn(name = "titulaire_id", nullable = false)
    private Client titulaireCarte;

    @ManyToOne
    @JoinColumn(name = "compte_id", nullable = false)
    private Compte compteAssocie;

    @OneToMany(mappedBy = "carte", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PaiementCarte> paiementCartes;


}
