package com.esiea.auroraskyesapi.reservation.mapper;

import com.esiea.auroraskyesapi.reservation.dto.ReservationGlobaleDTO;
import com.esiea.auroraskyesdbaccess.reservation.dto.ReservationBDDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

//TODO a faire lorsque API globale finis
@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "vol", ignore = true)
    ReservationBDDTO reservationGlobaleDTOToReservationBDDTO(ReservationGlobaleDTO reservationGlobaleDTO);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "volId", ignore = true)
    ReservationGlobaleDTO reservationBDDTOToReservationGlobaleDTO(ReservationBDDTO reservationBDDTO);

}

