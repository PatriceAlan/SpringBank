package com.project.SpringBank.DTO.client;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateClientRequestDTO {

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
    @Pattern(regexp = "\\+?[0-9.\\-()\\s]+")
    private String numeroTelephone;

    private String adressePostale;


}
