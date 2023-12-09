package com.project.SpringBank.repositories;

import com.project.SpringBank.entities.Carte;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteRepository extends CrudRepository<Carte, Long> {
}
