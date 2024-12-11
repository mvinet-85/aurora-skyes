package com.esiea.auroraskyesdbaccess.aeroport.mapper;

import com.esiea.auroraskyesdbaccess.aeroport.dto.AeroportBDDTO;
import com.esiea.auroraskyesdbaccess.aeroport.entity.AeroportEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AeroportMapper {

    List<AeroportBDDTO> aeroportEntitiesToAeroportBDDTO(List<AeroportEntity> aeroportEntities);

}
