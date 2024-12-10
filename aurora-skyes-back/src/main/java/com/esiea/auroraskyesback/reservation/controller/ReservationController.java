package com.esiea.auroraskyesback.reservation.controller;

import com.esiea.auroraskyesback.reservation.dto.ReservationDTO;
import com.esiea.auroraskyesback.reservation.mapper.ReservationMapper;
import com.esiea.auroraskyesback.reservation.service.ReservationService;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    // Map pour compter les réservations par vol et minute
    private final ConcurrentHashMap<Long, AtomicLong> reservationsCountPerVol = new ConcurrentHashMap<>();

    private final Sinks.Many<Map.Entry<Long, Long>> sink = Sinks.many().multicast().onBackpressureBuffer();

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
    public ReservationDTO createReservation(@RequestBody ReservationDTO reservationDTO) {
        ReservationDTO createdReservation = this.reservationMapper.toDTO(reservationService.createReservation(reservationDTO));
        Long volId = reservationDTO.getVolId();

        // Ajouter ou incrémenter le compteur pour cet ID de vol
        long count = reservationsCountPerVol
                .computeIfAbsent(volId, id -> new AtomicLong(0))
                .incrementAndGet();

        // Publier un événement dans le sink
        sink.tryEmitNext(Map.entry(volId, count));

        return createdReservation;
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

    /**
     * Endpoint permettant de récupérer les réservations d'un utilisateur
     * @param id de l'utilisateur
     * @return les réservations
     */
    @GetMapping("/user/{id}")
    public List<ReservationDTO> getUserReservation(@PathVariable Long id) {
        return reservationService.getUserReservation(id).stream().map(this.reservationMapper::toDTO).toList();
    }


    @GetMapping(value = "/historique/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<Long>> getReservationHistory(@PathVariable Long id) {
        return sink.asFlux()
                .filter(event -> event.getKey().equals(id))
                .map(event -> ServerSentEvent.<Long>builder()
                        .id(String.valueOf(event.getKey()))
                        .data(event.getValue())
                        .event("ReservationOK")
                        .build());
    }

}
