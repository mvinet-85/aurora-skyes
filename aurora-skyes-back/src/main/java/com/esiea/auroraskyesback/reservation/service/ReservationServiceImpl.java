package com.esiea.auroraskyesback.reservation.service;

import com.esiea.auroraskyesback.reservation.dao.ReservationDAO;
import com.esiea.auroraskyesback.reservation.dto.ReservationDTO;
import com.esiea.auroraskyesback.reservation.entity.ReservationEntity;
import com.esiea.auroraskyesback.reservation.exception.NoAvailableSeatsException;
import com.esiea.auroraskyesback.reservation.exception.ReservationNotFoundException;
import com.esiea.auroraskyesback.reservation.mapper.ReservationMapper;
import com.esiea.auroraskyesback.reservation.model.Classe;
import com.esiea.auroraskyesback.utilisateur.entity.UtilisateurEntity;
import com.esiea.auroraskyesback.utilisateur.service.UtilisateurService;
import com.esiea.auroraskyesback.vol.entity.VolEntity;
import com.esiea.auroraskyesback.vol.service.VolService;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

    /** {@link ReservationDAO} */
    private final ReservationDAO reservationDAO;

    /** {@link VolService} */
    private final VolService volService;

    /** {@link UtilisateurService} */
    private final UtilisateurService utilisateurService;

    /** {@link ReservationMapper} */
    private final ReservationMapper reservationMapper;

    /** {@link MeterRegistry} */
    private final MeterRegistry meterRegistry;

    public ReservationServiceImpl(ReservationDAO reservationDAO,
                                  VolService volService,
                                  UtilisateurService utilisateurService,
                                  ReservationMapper reservationMapper,
                                  MeterRegistry meterRegistry) {
        this.reservationDAO = reservationDAO;
        this.volService = volService;
        this.utilisateurService = utilisateurService;
        this.reservationMapper = reservationMapper;
        this.meterRegistry = meterRegistry;
    }

    /** {@inheritDoc} */
    @Transactional
    public ReservationEntity createReservation(ReservationDTO reservationDTO) {
        UtilisateurEntity user = utilisateurService.findUtilisateurById(reservationDTO.getUserId());
        VolEntity vol = volService.findVolById(reservationDTO.getVolId());

        if (vol.getPlaceDisponible() <= 0) {
            LOGGER.error("Aucune place disponible pour le vol ID : " + vol.getId());
            throw new NoAvailableSeatsException("Aucune place disponible pour le vol ID : " + vol.getId());
        }

        vol.setPlaceDisponible(vol.getPlaceDisponible() - 1);
        volService.modifierVol(vol);

        // Ajouter un compteur pour le vol
        Counter.builder("reservations.total")
                .description("Total des réservations par vol")
                .tags("volId", String.valueOf(vol.getId()))
                .register(meterRegistry)
                .increment();

        ReservationEntity reservation = reservationMapper.toEntity(reservationDTO);
        reservation.setUser(user);
        reservation.setVol(vol);

        if (Classe.FIRST.equals(reservationDTO.getClasse())) {
            reservation.setPrix(vol.getPrix() * 1.2);
        }
        else {
            reservation.setPrix(vol.getPrix());
        }

        if (vol.isEscale()) {
            reservation.setPrix(vol.getPrix() * 0.8);
        }

        return reservationDAO.save(reservation);
    }

    /** {@inheritDoc} */
    @Transactional
    public ReservationEntity updateReservation(ReservationDTO reservationDTO) {
        ReservationEntity existingReservation = reservationDAO.findById(reservationDTO.getId())
                .orElseThrow(() -> {
                    LOGGER.error("Réservation introuvable pour ID : " + reservationDTO.getId());
                    return new ReservationNotFoundException("Réservation introuvable pour ID : " + reservationDTO.getId());
                });


        volService.findVolById(reservationDTO.getVolId());

        existingReservation.setSiege(reservationDTO.getSiege());
        existingReservation.setClasse(reservationDTO.getClasse());

        return reservationDAO.save(existingReservation);
    }

    /** {@inheritDoc} */
    public ReservationEntity getReservation(Long id) {
        return reservationDAO.findById(id)
                .orElseThrow(() -> {
                    LOGGER.error("Réservation introuvable pour ID : " + id);
                    return new ReservationNotFoundException("Réservation introuvable pour ID : " + id);
                });
    }

    /** {@inheritDoc} */
    public List<ReservationEntity> getUserReservation(Long id) {
        return reservationDAO.findByUserId(id);
    }

}
