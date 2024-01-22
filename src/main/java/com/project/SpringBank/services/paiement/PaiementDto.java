package com.project.SpringBank.services.paiement;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.project.SpringBank.entities.Paiement}
 */

public record PaiementDto(Long idPaiement, int codeSecurite, double montantPaiement,
                          LocalDate datePaiement) implements Serializable {
}