package com.esiea.auroraskyesback.reservation.controller;

import com.esiea.auroraskyesback.reservation.dto.ReservationDTO;
import com.esiea.auroraskyesback.reservation.mapper.ReservationMapper;
import com.esiea.auroraskyesback.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    /** {@link ReservationService} */
    private final ReservationService reservationService;

    /** {@link ReservationMapper} */
    private final ReservationMapper reservationMapper;

    @Autowired
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
    public ReservationDTO createReservation(@RequestBody ReservationDTO reservationDTO) {
        return this.reservationMapper.toDTO(reservationService.createReservation(reservationDTO));
    }

    /**
     * Endpoint permettant de modifier la reservation
     * @param reservationDTO informations mise a jour de la réservation
     * @return la réservation modifiée
     */
    @PutMapping()
    public ReservationDTO updateReservation(@RequestBody ReservationDTO reservationDTO) {
        return this.reservationMapper.toDTO(reservationService.updateReservation(reservationDTO));
    }

    /**
     * Endpoint permettant de récupérer une réservation
     * @param id de la réservation a récupérer
     * @return la réservation
     */
    @GetMapping("/{id}")
    public ReservationDTO getReservation(@PathVariable Long id) {
        return this.reservationMapper.toDTO(reservationService.getReservation(id));
    }

}