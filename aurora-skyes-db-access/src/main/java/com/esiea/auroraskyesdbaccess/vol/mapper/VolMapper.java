package com.esiea.auroraskyesdbaccess.vol.mapper;

import com.esiea.auroraskyesdbaccess.vol.dto.VolBDDTO;
import com.esiea.auroraskyesdbaccess.vol.entity.VolEntity;
import com.esiea.auroraskyesdbaccess.vol.entity.VolExternalEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VolMapper {

    VolBDDTO volEntityToVolBDDTO(VolEntity volEntity);

    VolBDDTO volExternalEntityToVolBDDTO(VolExternalEntity volExternalEntity);

    List<VolBDDTO> volEntitiesToVolBDDTO(List<VolEntity> volEntities);

    VolEntity volBDDTOToVolVolEntity(VolBDDTO volBDDTO);

    VolExternalEntity volBDDTOToVolExternalEntity(VolBDDTO volBDDTO);

}

