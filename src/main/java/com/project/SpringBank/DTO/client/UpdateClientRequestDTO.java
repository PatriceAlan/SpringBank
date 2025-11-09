package com.project.SpringBank.DTO.client;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UpdateClientRequestDTO {

    private String prenom;

    private String nom;

    @Past
    @Temporal(TemporalType.DATE)
    private LocalDate dateNaissance;

    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    @NotBlank
    @NotEmpty
    private String numeroTelephone;

    private String adressePostale;

    private LocalDateTime dateModification;


}
