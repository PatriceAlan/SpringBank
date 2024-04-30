package com.project.SpringBank.DTO.carte;

import com.project.SpringBank.entities.Client;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarteDTO {

    private Client titulaireCarte;
    private Long numeroCarte;
    private LocalDate dateExpiration;
    private int codeSecurite;

}
