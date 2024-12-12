package com.esiea.auroraskyesdbaccess.reservation.mapper;

import com.esiea.auroraskyesdbaccess.reservation.dto.ReservationBDDTO;
import com.esiea.auroraskyesdbaccess.reservation.entity.ReservationEntity;
import com.esiea.auroraskyesdbaccess.reservation.entity.ReservationExternalEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    ReservationEntity reservationBDDTOToReservationEntity(ReservationBDDTO reservationBDDTO);

    ReservationBDDTO reservationEntityToReservationBDDTO(ReservationEntity reservationEntity);

    List<ReservationBDDTO> reservationEntitiesToReservationBDDTO(List<ReservationEntity> reservationEntities);

    ReservationExternalEntity reservationBDDTOToReservationExternalEntity(ReservationBDDTO reservationBDDTO);

    ReservationBDDTO reservationExternalEntityToReservationBDDTO(ReservationExternalEntity reservationExternalEntity);
}
