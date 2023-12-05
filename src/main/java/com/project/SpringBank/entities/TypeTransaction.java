package com.project.SpringBank.entities;

import lombok.Getter;

@Getter
public enum TypeTransaction {
    DEBIT("Débit"),
    CREDIT("Crédit");

    private final String label;

    TypeTransaction(String label) {
        this.label = label;
    }

}
