package com.esiea.auroraskyesback.reservation.mapper;

import com.esiea.auroraskyesback.reservation.dto.ReservationDTO;
import com.esiea.auroraskyesdbaccess.reservation.dto.ReservationBDDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "vol", ignore = true)
    ReservationBDDTO reservationDTOToReservationBDDTO(ReservationDTO reservationDTO);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "volId", ignore = true)
    ReservationDTO reservationBDDTOToReservationDTO(ReservationBDDTO reservationBDDTO);

    List<ReservationDTO> reservationBDDTOSToReservationDTO(List<ReservationBDDTO> reservationBDDTOS);
}

