package com.esiea.auroraskyesapi.vol.dto;

import lombok.Data;

import java.util.Date;

@Data
public class VolGlobalDTO {

    /** Id du vol */
    private Long id;

    /** Date de départ */
    private Date departureDate;

    /** Date d'arrivée */
    private Date arrivalDate;

    /** CODE Aéroport de départ */
    private String departureAirportCodeDTO;

    /** CODE  Aéroport d'arrivée */
    private String arrivalAirportCodeDTO;

    /** Nombre de place pour le vol */
    private int capacity;

    /** Prix du vol */
    private double price;

    /** Id du groupe */
    private long groupId;

}
