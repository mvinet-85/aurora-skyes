package com.esiea.auroraskyesback.reservation.mapper;

import com.esiea.auroraskyesback.reservation.dto.ReservationDTO;
import com.esiea.auroraskyesback.reservation.entity.ReservationEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReservationMapper {

    public ReservationDTO toDTO(ReservationEntity reservation) {
        if (reservation == null) {
            return null;
        }
        ReservationDTO dto = new ReservationDTO();
        dto.setId(reservation.getId());
        dto.setUserId(reservation.getUser().getId());
        dto.setVolId(reservation.getVol().getId());
        dto.setSiege(reservation.getSiege());
        dto.setClasse(reservation.getClasse());
        return dto;
    }

    public ReservationEntity toEntity(ReservationDTO reservationDTO) {
        if (reservationDTO == null) {
            return null;
        }
        ReservationEntity entity = new ReservationEntity();
        entity.setId(reservationDTO.getId());
        entity.setSiege(reservationDTO.getSiege());
        entity.setClasse(reservationDTO.getClasse());
        return entity;
    }
}
