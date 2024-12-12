package com.esiea.auroraskyesapi.reservation.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReservationGlobaleDTO {

    /** Id de la reservation */
    private Long id;

    /** Utilisateur */
    private Long userId;

    /** Vol associé */
    private Long flightId;

    /** Liste des options */
    private List<String> option;

    /** Monnaie */
    private String currency;

    /** Prix */
    private double price;

}
