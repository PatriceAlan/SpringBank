package com.project.SpringBank.repositories;

import com.project.SpringBank.entities.Transaction;
import com.project.SpringBank.entities.TypeSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
