package com.esiea.auroraskyesdbaccess.vol.dto;

import com.esiea.auroraskyesdbaccess.aeroport.dto.AeroportBDDTO;
import lombok.Data;

import java.util.Date;

@Data
public class VolBDDTO {

    /** Id du vol */
    private Long id;

    /** Date de départ */
    private Date dateDepart;

    /** Date d'arrivée */
    private Date dateArrive;

    /** Aéroport de départ */
    private AeroportBDDTO aeroportDepart;

    /** Aéroport d'arrivée */
    private AeroportBDDTO aeroportArrivee;

    /** Nombre de place pour le vol */
    private int placeDisponible;

    /** Prix du vol */
    private int prix;

    /** Escale */
    private boolean escale;

}
