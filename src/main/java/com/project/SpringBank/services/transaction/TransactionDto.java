package com.project.SpringBank.services.transaction;

import com.project.SpringBank.entities.TypeSource;
import com.project.SpringBank.entities.TypeTransaction;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.project.SpringBank.entities.Transaction}
 */
public record TransactionDto(Long idTransaction, TypeTransaction typeTransaction, TypeSource typeSource, Long idSource,
                             LocalDate dateTransaction, double montant) implements Serializable {
}