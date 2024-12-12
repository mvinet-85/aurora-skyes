package com.esiea.auroraskyesapi.aeroport.mapper;

import com.esiea.auroraskyesapi.aeroport.dto.AeroportGlobaleDTO;
import com.esiea.auroraskyesapi.vol.dto.VolGlobalDTO;
import com.esiea.auroraskyesdbaccess.aeroport.dto.AeroportBDDTO;
import com.esiea.auroraskyesdbaccess.vol.dto.VolBDDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AeroportMapper {

    List<AeroportGlobaleDTO> aeroportBDDTOSToAeroportGlobaleDTO(List<AeroportBDDTO> aeroportBDDTOS);

    AeroportGlobaleDTO aeroportBDDTOToAeroportGlobaleDTO(AeroportBDDTO aeroportBDDTO);

    AeroportBDDTO aeroportGlobaleDTOToAeroportBDDTO(AeroportGlobaleDTO aeroportGlobaleDTO);

}
