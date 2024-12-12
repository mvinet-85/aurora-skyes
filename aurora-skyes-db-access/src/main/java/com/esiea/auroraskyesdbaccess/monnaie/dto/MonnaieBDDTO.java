package com.esiea.auroraskyesdbaccess.monnaie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonnaieBDDTO {

    /** Nom de la monnaie */
    private String nom;

    /** Taux de la monnaie */
    private double taux;

}

