package com.esiea.auroraskyesback.reservation.service;

import com.esiea.auroraskyesback.reservation.dto.ReservationDTO;
import com.esiea.auroraskyesback.reservation.entity.ReservationEntity;

public interface ReservationService {

    /**
     * Créer une réservation avec les informations
     * @param reservationDTO informations de la réservation
     * @return la réservation créée
     */
    ReservationEntity createReservation(ReservationDTO reservationDTO);

    /**
     * Modifie la réservation
     * @param reservationDTO informations de la réservation a modifier
     * @return la réservation modifiée
     */
    ReservationEntity updateReservation(ReservationDTO reservationDTO);

    /**
     * Récupère une réservation
     * @param id de la réservation
     * @return la réservation
     */
    ReservationEntity getReservation(Long id);

}
