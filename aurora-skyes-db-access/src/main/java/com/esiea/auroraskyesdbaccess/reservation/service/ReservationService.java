package com.esiea.auroraskyesdbaccess.reservation.service;

import com.esiea.auroraskyesdbaccess.reservation.dto.ReservationBDDTO;
import com.esiea.auroraskyesdbaccess.reservation.entity.ReservationEntity;

import java.util.List;

public interface ReservationService {

    /**
     * Créer une réservation avec les informations
     * @param reservationDTO informations de la réservation
     * @return la réservation créée
     */
    ReservationEntity createReservation(ReservationBDDTO reservationDTO);

    /**
     * Modifie la réservation
     * @param reservationDTO informations de la réservation a modifier
     * @return la réservation modifiée
     */
    ReservationEntity updateReservation(ReservationBDDTO reservationDTO);

    /**
     * Récupère une réservation
     * @param id de la réservation
     * @return la réservation
     */
    ReservationEntity getReservation(Long id);

    /**
     * Récupère les réservation
     * @param id de l'utilisateur
     * @return les réservations
     */
    List<ReservationEntity> getUserReservation(Long id);

}
