package com.project.SpringBank.DTO.compte;

import com.project.SpringBank.entities.Client;
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
    private Set<Client> titulaireCompte;
    private String intituleCompte;
    private LocalDateTime dateCreation;
}
