package com.esiea.auroraskyesapi.aeroport.mapper;

import com.esiea.auroraskyesapi.aeroport.dto.AeroportGlobaleDTO;
import com.esiea.auroraskyesdbaccess.aeroport.dto.AeroportBDDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AeroportMapper {

    List<AeroportGlobaleDTO> aeroportBDDTOSToAeroportGlobaleDTO(List<AeroportBDDTO> aeroportBDDTOS);

}
