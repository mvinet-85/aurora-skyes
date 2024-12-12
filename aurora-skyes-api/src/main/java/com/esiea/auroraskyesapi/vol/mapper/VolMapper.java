package com.esiea.auroraskyesapi.vol.mapper;

import com.esiea.auroraskyesapi.vol.dto.VolGlobalDTO;
import com.esiea.auroraskyesdbaccess.vol.dto.VolBDDTO;
import org.mapstruct.Mapper;

import java.util.List;

//TODO a faire une fois l'api globale terminée
@Mapper(componentModel = "spring")
public interface VolMapper {

    VolGlobalDTO volBDDTOToVolGlobalDTO(VolBDDTO volBDDTO);

    List<VolGlobalDTO> volBDDTOSToVolGlobalDTO(List<VolBDDTO> volBDDTOS);

}

