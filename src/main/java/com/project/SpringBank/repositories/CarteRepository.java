package com.project.SpringBank.repositories;

import com.project.SpringBank.entities.Carte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarteRepository extends JpaRepository<Carte, Long> {
    List<Carte> findByCompteAssocie(Long compteId);
}
