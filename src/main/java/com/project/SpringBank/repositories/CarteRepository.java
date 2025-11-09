package com.project.SpringBank.repositories;

import com.project.SpringBank.entities.Carte;
import com.project.SpringBank.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarteRepository extends JpaRepository<Carte, Long> {
    List<Carte> findByCompteAssocie_Iban(String iban);

    Optional<Carte> findByNumeroCarte(Long numeroCarte);
}
