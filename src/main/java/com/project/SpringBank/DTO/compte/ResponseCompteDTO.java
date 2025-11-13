package com.project.SpringBank.DTO.compte;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCompteDTO {

    private String iban;
    private Long numeroCompte;
    private String typeCompte;
    private Set<Long> titulaireCompte;
    private String intituleCompte;
    private LocalDateTime dateCreation;
}
