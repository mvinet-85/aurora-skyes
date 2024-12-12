package com.esiea.auroraskyesback.vol.mapper;

import com.esiea.auroraskyesback.vol.dto.VolDTO;
import com.esiea.auroraskyesdbaccess.vol.dto.VolBDDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VolMapper {

    VolDTO volBDDTOToVolDTO(VolBDDTO volBDDTO);

    List<VolDTO> volBDDTOSToVolDTO(List<VolBDDTO> volBDDTOS);

}

