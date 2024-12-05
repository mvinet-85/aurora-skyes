package com.esiea.auroraskyesback.reservation.dto;

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
    private String classe;

    /** Siege */
    private String siege;

}
