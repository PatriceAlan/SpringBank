package com.project.SpringBank.repositories;

import com.project.SpringBank.entities.Virement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VirementRepository extends JpaRepository<Virement, Long> {
}
