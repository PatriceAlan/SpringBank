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
    private Long numeroCarte;
    private LocalDateTime dateExpiration;
}
