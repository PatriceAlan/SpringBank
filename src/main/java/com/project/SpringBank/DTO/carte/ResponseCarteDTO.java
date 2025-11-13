package com.project.SpringBank.DTO.carte;

import com.project.SpringBank.entities.Client;
import lombok.*;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseCarteDTO {

    private Long titulaireCarte;
    private Long numeroCarte;
    private LocalDate dateExpiration;



}
