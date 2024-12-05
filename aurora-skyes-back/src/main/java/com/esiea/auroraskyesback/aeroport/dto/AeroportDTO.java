package com.esiea.auroraskyesback.aeroport.dto;

import lombok.Data;

@Data
public class AeroportDTO {

    /** Id de l'aeroport */
    private Long id;

    /** Nom de l'aéroport */
    private String nom;

    /** Ville de l'aeroport */
    private String ville;

}
