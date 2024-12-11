package com.esiea.auroraskyesback.reservation.service;

import com.esiea.auroraskyesback.reservation.dto.ReservationDTO;
import com.esiea.auroraskyesdbaccess.reservation.dto.ReservationBDDTO;

import java.util.List;

public interface ReservationService {

    /**
     * Créer une réservation avec les informations
     * @param reservationDTO informations de la réservation
     * @return la réservation créée
     */
    ReservationBDDTO createReservation(ReservationDTO reservationDTO);

    /**
     * Modifie la réservation
     * @param reservationDTO informations de la réservation a modifier
     * @return la réservation modifiée
     */
    ReservationBDDTO updateReservation(ReservationDTO reservationDTO);

    /**
     * Récupère une réservation
     * @param id de la réservation
     * @return la réservation
     */
    ReservationBDDTO getReservation(Long id);

    /**
     * Récupère les réservation
     * @param id de l'utilisateur
     * @return les réservations
     */
    List<ReservationBDDTO> getUserReservation(Long id);

}
