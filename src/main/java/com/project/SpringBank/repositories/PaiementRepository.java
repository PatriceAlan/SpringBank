package com.project.SpringBank.repositories;

import com.project.SpringBank.entities.PaiementCarte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaiementRepository extends JpaRepository<PaiementCarte, Long> {
    List<PaiementCarte> findByNumeroCarte(String numeroCarte);
}
