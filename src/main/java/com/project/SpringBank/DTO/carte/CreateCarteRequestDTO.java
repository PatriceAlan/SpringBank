package com.project.SpringBank.DTO.carte;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCarteRequestDTO {

    private Long titulaireCarte;
    private int codeSecurite;

}
