package com.project.SpringBank.services.carte;

import com.project.SpringBank.entities.Carte;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Carte}
 */
@EqualsAndHashCode(callSuper = true)
@Value
public record CarteDto(Long numeroCarte, int codeSecurite,
                       @NotNull @Future LocalDate dateExpiration) implements Serializable {
}