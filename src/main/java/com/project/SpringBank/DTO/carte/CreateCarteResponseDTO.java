package com.project.SpringBank.DTO.carte;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateCarteResponseDTO {

    private Long titulaireCarte;
    private String numeroCarte;
    private LocalDateTime dateExpiration;
}
