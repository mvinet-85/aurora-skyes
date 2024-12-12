package com.esiea.auroraskyesdbaccess.reservation.service;

import com.esiea.auroraskyesdbaccess.reservation.dao.ReservationDAO;
import com.esiea.auroraskyesdbaccess.reservation.dto.ReservationBDDTO;
import com.esiea.auroraskyesdbaccess.reservation.entity.ReservationEntity;
import com.esiea.auroraskyesdbaccess.reservation.exception.ReservationNotFoundException;
import com.esiea.auroraskyesdbaccess.reservation.mapper.ReservationMapper;

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

    /** {@link ReservationMapper} */
    private final ReservationMapper reservationMapper;

    public ReservationServiceImpl(ReservationDAO reservationDAO,
                                  ReservationMapper reservationMapper) {
        this.reservationDAO = reservationDAO;
        this.reservationMapper = reservationMapper;
    }

    /** {@inheritDoc} */
    @Transactional
    public ReservationEntity createReservation(ReservationBDDTO reservationDTO) {
        return reservationDAO.save(this.reservationMapper.reservationBDDTOToReservationEntity(reservationDTO));
    }

    /** {@inheritDoc} */
    @Transactional
    public ReservationEntity updateReservation(ReservationBDDTO reservationDTO) {
        return reservationDAO.save(this.reservationMapper.reservationBDDTOToReservationEntity(reservationDTO));
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
