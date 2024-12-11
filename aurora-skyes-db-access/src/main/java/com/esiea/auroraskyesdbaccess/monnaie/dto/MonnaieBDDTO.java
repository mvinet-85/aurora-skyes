package com.esiea.auroraskyesdbaccess.monnaie.dto;

import lombok.Data;

@Data
public class MonnaieBDDTO {

    /** Nom de la monnaie */
    private final String nom;

    /** Taux de la monnaie */
    private final double taux;

    public MonnaieBDDTO(String nom, double taux) {
        this.nom = nom;
        this.taux = taux;
    }

}

