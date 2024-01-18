package com.project.SpringBank.services.compte;

import com.project.SpringBank.entities.TypeCompte;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.project.SpringBank.entities.Compte}
 */
@EqualsAndHashCode(callSuper = true)
@Value
public record CompteDto(String iban, Long numeroCompte, double solde, TypeCompte typeCompte,
                        String intituleCompte) implements Serializable {
}