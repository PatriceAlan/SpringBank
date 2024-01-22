package com.project.SpringBank.services.client;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.project.SpringBank.entities.Client}
 */
public record ClientDto(Long idClient, @NotNull @NotEmpty @NotBlank String prenom,
                        @NotNull @NotEmpty @NotBlank String nom, @NotNull @Past LocalDate dateNaissance,
                        @Size(max = 100) @Email @NotBlank String email,
                        @NotNull @NotEmpty @NotBlank String numeroTelephone, String adressePostale,
                        @PastOrPresent LocalDate dateCreation) implements Serializable {
}