package com.esiea.auroraskyesapi.reservation.service;

import com.esiea.auroraskyesapi.reservation.dto.ReservationGlobaleDTO;
import com.esiea.auroraskyesdbaccess.reservation.dto.ReservationBDDTO;

public interface ReservationService {

    /**
     * Créer une réservation avec les informations
     * @param reservationGlobaleDTO informations de la réservation
     * @return la réservation créée
     */
    ReservationBDDTO createReservation(ReservationGlobaleDTO reservationGlobaleDTO);

    /**
     * Récupère une réservation
     * @param id de la réservation
     * @return la réservation
     */
    ReservationBDDTO getReservation(Long id);

}
