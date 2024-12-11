package com.esiea.auroraskyesdbaccess.reservation.mapper;

import com.esiea.auroraskyesdbaccess.reservation.dto.ReservationBDDTO;
import com.esiea.auroraskyesdbaccess.reservation.entity.ReservationEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    ReservationEntity reservationBDDTOToReservationEntity(ReservationBDDTO reservationBDDTO);

    ReservationBDDTO reservationEntityToReservationBDDTO(ReservationEntity reservationEntity);

    List<ReservationBDDTO> reservationEntitiesToReservationBDDTO(List<ReservationEntity> reservationEntities);
}
