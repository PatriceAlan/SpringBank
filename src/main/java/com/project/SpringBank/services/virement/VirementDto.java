package com.project.SpringBank.services.virement;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.project.SpringBank.entities.Virement}
 */
public record VirementDto(Long idVirement, String libelleVirement, double montantVirement,
                          LocalDate dateVirement) implements Serializable {
}