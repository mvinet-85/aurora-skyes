package com.esiea.auroraskyesdbaccess.reservation.controller;

import com.esiea.auroraskyesdbaccess.reservation.dto.ReservationBDDTO;
import com.esiea.auroraskyesdbaccess.reservation.mapper.ReservationMapper;
import com.esiea.auroraskyesdbaccess.reservation.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    /** {@link ReservationService} */
    private final ReservationService reservationService;

    /** {@link ReservationMapper} */
    private final ReservationMapper reservationMapper;

    public ReservationController(ReservationService reservationService,
                                 ReservationMapper reservationMapper) {
        this.reservationService = reservationService;
        this.reservationMapper = reservationMapper;
    }

    /**
     * Endpoint permettant de créer une réservation
     * @param reservationDTO informations de la réservation
     * @return la réservation créée
     */
    @PostMapping
    public ReservationBDDTO createReservation(@RequestBody ReservationBDDTO reservationDTO) {
        return this.reservationMapper.reservationEntityToReservationBDDTO(this.reservationService.createReservation(reservationDTO));
    }

    /**
     * Endpoint permettant de récupérer une réservation
     * @param id de la réservation a récupérer
     * @return la réservation
     */
    @GetMapping("/{id}")
    public ReservationBDDTO getReservation(@PathVariable Long id) {
        return this.reservationMapper.reservationEntityToReservationBDDTO(this.reservationService.getReservation(id));
    }

    /**
     * Endpoint permettant de récupérer les réservations d'un utilisateur
     * @param id de l'utilisateur
     * @return les réservations
     */
    @GetMapping("/user/{id}")
    public List<ReservationBDDTO> getUserReservation(@PathVariable Long id) {
        return this.reservationMapper.reservationEntitiesToReservationBDDTO(this.reservationService.getUserReservation(id));
    }

    /**
     * Endpoint permettant de créer une réservation via l'api
     * @param reservationDTO informations de la réservation
     * @return la réservation créée
     */
    @PostMapping("/external")
    public ReservationBDDTO createReservationExternal(@RequestBody ReservationBDDTO reservationDTO) {
        return this.reservationMapper.reservationExternalEntityToReservationBDDTO(this.reservationService.createReservationExternal(reservationDTO));
    }

}
