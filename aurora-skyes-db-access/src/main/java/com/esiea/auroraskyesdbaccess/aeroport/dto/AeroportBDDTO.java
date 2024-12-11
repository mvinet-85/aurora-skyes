package com.esiea.auroraskyesdbaccess.aeroport.dto;

import lombok.Data;

@Data
public class AeroportBDDTO {

    /** Id de l'aeroport */
    private Long id;

    /** Nom de l'aéroport */
    private String nom;

    /** Ville de l'aeroport */
    private String ville;

}
