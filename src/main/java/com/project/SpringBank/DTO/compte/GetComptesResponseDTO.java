package com.project.SpringBank.DTO.compte;

import com.project.SpringBank.DTO.transaction.GetTransactionsCompteResponseDTO;
import com.project.SpringBank.entities.TypeCompte;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GetComptesResponseDTO {

    private String iban;
    private double solde;
    private String intituleCompte;
    private TypeCompte typeCompte;
    private Set<Long> titulairesCompte;
    private List<GetTransactionsCompteResponseDTO> transactions = new ArrayList<>();

}
