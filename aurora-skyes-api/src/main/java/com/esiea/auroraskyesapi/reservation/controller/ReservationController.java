package com.esiea.auroraskyesapi.reservation.controller;

import com.esiea.auroraskyesapi.reservation.dto.ReservationGlobaleDTO;
import com.esiea.auroraskyesapi.reservation.mapper.ReservationMapper;
import com.esiea.auroraskyesapi.reservation.service.ReservationService;
import org.springframework.web.bind.annotation.*;

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
     *
     * @param reservationGlobaleDTO informations de la réservation
     * @return la réservation créée
     */
    @PostMapping
    public ReservationGlobaleDTO createReservation(@RequestBody ReservationGlobaleDTO reservationGlobaleDTO) {
        return this.reservationMapper.reservationBDDTOToReservationGlobaleDTO(reservationService.createReservation(reservationGlobaleDTO));
    }

    /**
     * Endpoint permettant de récupérer une réservation
     *
     * @param id de la réservation a récupérer
     * @return la réservation
     */
    @GetMapping("/{id}")
    public ReservationGlobaleDTO getReservation(@PathVariable Long id) {
        return this.reservationMapper.reservationBDDTOToReservationGlobaleDTO(reservationService.getReservation(id));
    }

}
