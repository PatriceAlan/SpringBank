package com.project.SpringBank.repositories;

import com.project.SpringBank.DTO.compte.ResponseCompteDTO;
import com.project.SpringBank.entities.Compte;
import com.project.SpringBank.entities.TypeCompte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompteRepository extends JpaRepository<Compte, String>{

    Optional<Compte> findByIban(String iban);
}
