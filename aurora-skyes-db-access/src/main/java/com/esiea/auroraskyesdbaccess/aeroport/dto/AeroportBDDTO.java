package com.esiea.auroraskyesdbaccess.aeroport.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AeroportBDDTO {

    /** Id de l'aeroport */
    private Long id;

    /** Nom de l'aéroport */
    private String nom;

    /** Ville de l'aeroport */
    private String ville;

}
