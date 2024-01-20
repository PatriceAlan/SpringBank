package com.project.SpringBank.services.compte;

import com.project.SpringBank.entities.TypeCompte;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.project.SpringBank.entities.Compte}
 */
public record CompteDto(String iban, Long numeroCompte, double solde, int cleRIB, TypeCompte typeCompte,
                        String intituleCompte, LocalDate dateCreation) implements Serializable {
}