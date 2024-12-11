package com.esiea.auroraskyesback.reservation.dto;

import com.esiea.auroraskyesdbaccess.reservation.model.Classe;
import lombok.Data;

@Data
public class ReservationDTO {

    /** Id de la reservation */
    private Long id;

    /** Utilisateur */
    private Long userId;

    /** Vol associé */
    private Long volId;

    /** CLasse */
    private Classe classe;

    /** Siege */
    private String siege;

    /** Prix */
    private double prix;

}
