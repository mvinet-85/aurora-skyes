package com.esiea.auroraskyesback.reservation.service;

import com.esiea.auroraskyesback.reservation.dao.ReservationDAO;
import com.esiea.auroraskyesback.reservation.dto.ReservationDTO;
import com.esiea.auroraskyesback.reservation.entity.ReservationEntity;
import com.esiea.auroraskyesback.reservation.mapper.ReservationMapper;
import com.esiea.auroraskyesback.utilisateur.entity.UtilisateurEntity;
import com.esiea.auroraskyesback.utilisateur.service.UtilisateurService;
import com.esiea.auroraskyesback.vol.entity.VolEntity;
import com.esiea.auroraskyesback.vol.service.VolService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservationServiceImpl implements ReservationService {

    /** {@link ReservationDAO} */
    private final ReservationDAO reservationDAO;

    /** {@link VolService} */
    private final VolService volService;

    /** {@link UtilisateurService} */
    private final UtilisateurService utilisateurService;

    /** {@link ReservationMapper} */
    private final ReservationMapper reservationMapper;

    public ReservationServiceImpl(ReservationDAO reservationDAO,
                                  VolService volService,
                                  UtilisateurService utilisateurService,
                                  ReservationMapper reservationMapper) {
        this.reservationDAO = reservationDAO;
        this.volService = volService;
        this.utilisateurService = utilisateurService;
        this.reservationMapper = reservationMapper;
    }

    /** {@inheritDoc} */
    @Transactional
    public ReservationEntity createReservation(ReservationDTO reservationDTO) {
        UtilisateurEntity user = utilisateurService.findUtilisateurById(reservationDTO.getUserId());

        VolEntity vol = volService.findVolById(reservationDTO.getVolId());

        if (vol.getPlaceDisponible() <= 0) {
            throw new RuntimeException("Aucune place disponible pour le vol");
        }

        vol.setPlaceDisponible(vol.getPlaceDisponible() - 1);
        volService.modifierVol(vol);

        ReservationEntity reservation = reservationMapper.toEntity(reservationDTO);
        reservation.setUser(user);
        reservation.setVol(vol);

        return reservationDAO.save(reservation);
    }

    /** {@inheritDoc} */
    @Transactional
    public ReservationEntity updateReservation(ReservationDTO reservationDTO) {

        ReservationEntity existingReservation = reservationDAO.findById(reservationDTO.getId())
                .orElseThrow(() -> new RuntimeException("Réservation introuvable"));

        volService.findVolById(reservationDTO.getVolId());

        existingReservation.setSiege(reservationDTO.getSiege());
        existingReservation.setClasse(reservationDTO.getClasse());

        return reservationDAO.save(existingReservation);
    }

    /** {@inheritDoc} */
    public ReservationEntity getReservation(Long id) {
        return reservationDAO.findById(id).orElseThrow(() -> new RuntimeException("Réservation introuvable"));
    }

}
