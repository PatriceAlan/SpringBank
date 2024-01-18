package com.project.SpringBank.services.client;

import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.project.SpringBank.entities.Client}
 */
@EqualsAndHashCode(callSuper = true)
@Value
public record ClientDto(@NotNull @NotEmpty @NotBlank String prenom, @NotNull @NotEmpty @NotBlank String nom,
                        @NotNull @Past LocalDate dateNaissance, @Size(max = 100) @Email @NotBlank String email,
                        @NotNull @NotEmpty @NotBlank String numeroTelephone,
                        String adressePostale) implements Serializable {
}