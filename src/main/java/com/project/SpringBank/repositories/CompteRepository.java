package com.project.SpringBank.repositories;

import com.project.SpringBank.DTO.compte.ResponseCompteDTO;
import com.project.SpringBank.entities.Compte;
import com.project.SpringBank.entities.TypeCompte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompteRepository extends JpaRepository<Compte, String>{
    List<Compte> findByTypeCompte(TypeCompte typeCompte);

    ResponseCompteDTO getCompteByIban(String iban);
}
