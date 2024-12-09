package com.esiea.auroraskyesback.vol.dto;

import com.esiea.auroraskyesback.aeroport.dto.AeroportDTO;
import lombok.Data;

import java.util.Date;

@Data
public class VolDTO {

    /** Id du vol */
    private Long id;

    /** Date de départ */
    private Date dateDepart;

    /** Date d'arrivée */
    private Date dateArrive;

    /** Aéroport de départ */
    private AeroportDTO aeroportDepart;

    /** Aéroport d'arrivée */
    private AeroportDTO aeroportArrivee;

    /** Nombre de place pour le vol */
    private int placeDisponible;

    /** Prix du vol */
    private int prix;

    /** Escale */
    private boolean escale;

}
