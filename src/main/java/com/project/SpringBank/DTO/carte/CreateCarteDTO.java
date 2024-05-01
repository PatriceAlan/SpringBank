package com.project.SpringBank.DTO.carte;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarteDTO {

    private Long titulaireCarte;
    private Long numeroCarte;
    private LocalDate dateExpiration;
    private int codeSecurite;

}
