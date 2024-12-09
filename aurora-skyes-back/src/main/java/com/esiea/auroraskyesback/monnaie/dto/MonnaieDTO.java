package com.esiea.auroraskyesback.monnaie.dto;

import lombok.Data;

@Data
public class MonnaieDTO {

    /** Nom de la monnaie */
    private final String nom;

    /** Taux de la monnaie */
    private final double taux;

    public MonnaieDTO(String nom, double taux) {
        this.nom = nom;
        this.taux = taux;
    }

}

