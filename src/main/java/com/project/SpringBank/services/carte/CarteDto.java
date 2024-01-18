package com.project.SpringBank.services.carte;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.project.SpringBank.entities.Carte}
 */
public record CarteDto(Long numeroCarte, int codeSecurite, LocalDate dateExpiration) implements Serializable {
}