package com.project.SpringBank.DTO.carte;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetCarteResponseDTO {

    private Long numeroCarte;
    private LocalDateTime dateExpiration;
    private Long titulaireCarte;
}
