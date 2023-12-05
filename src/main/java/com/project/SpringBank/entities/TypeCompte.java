package com.project.SpringBank.entities;

import lombok.Getter;

@Getter
public enum TypeCompte {
    COMPTE_COURANT_SIMPLE("Compte Courant Simple"),
    COMPTE_COURANT_JOINT("Compte Courant Joint");

    private final String label;

    TypeCompte(String label) {
        this.label = label;
    }

}
