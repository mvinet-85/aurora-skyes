package com.esiea.auroraskyesdbaccess.vol.mapper;

import com.esiea.auroraskyesdbaccess.vol.dto.VolBDDTO;
import com.esiea.auroraskyesdbaccess.vol.entity.VolEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VolMapper {

    VolBDDTO volEntityToVolBDDTO(VolEntity volEntity);

    List<VolBDDTO> volEntitiesToVolBDDTO(List<VolEntity> volEntities);

}

