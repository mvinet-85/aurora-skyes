package com.esiea.auroraskyesback.aeroport.mapper;

import com.esiea.auroraskyesback.aeroport.dto.AeroportDTO;
import com.esiea.auroraskyesdbaccess.aeroport.dto.AeroportBDDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AeroportMapper {

    List<AeroportDTO> aeroportBDDTOSToAeroportDTO(List<AeroportBDDTO> aeroportBDDTOS);

}
