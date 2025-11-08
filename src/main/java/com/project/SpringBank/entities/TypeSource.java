package com.project.SpringBank.entities;

import lombok.Getter;

@Getter
public enum TypeSource {
    VIREMENT("Virement"),
    CARTE("Carte");

    private final String label;

    TypeSource(String label) {
        this.label = label;
    }

}

