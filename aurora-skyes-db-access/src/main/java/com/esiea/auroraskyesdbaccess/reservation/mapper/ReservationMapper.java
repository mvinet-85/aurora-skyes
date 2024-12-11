package com.esiea.auroraskyesdbaccess.reservation.mapper;

import com.esiea.auroraskyesdbaccess.reservation.dto.ReservationBDDTO;
import com.esiea.auroraskyesdbaccess.reservation.entity.ReservationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mapping(target = "vol", ignore = true)
    @Mapping(target = "user", ignore = true)
    ReservationEntity reservationBDDTOToReservationEntity(ReservationBDDTO reservationBDDTO);

    @Mapping(source = "vol.id", target = "volId")
    @Mapping(source = "user.id", target = "userId")
    ReservationBDDTO reservationEntityToReservationBDDTO(ReservationEntity reservationEntity);

    @Mapping(source = "vol.id", target = "volId")
    @Mapping(source = "user.id", target = "userId")
    List<ReservationBDDTO> reservationEntitiesToReservationBDDTO(List<ReservationEntity> reservationEntities);
}
