package com.esiea.auroraskyesback.aeroport.mapper;

import com.esiea.auroraskyesback.aeroport.dto.AeroportDTO;
import com.esiea.auroraskyesback.aeroport.entity.AeroportEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AeroportMapper {

    List<AeroportDTO> aeroportEntitiesToAeroportDTO(List<AeroportEntity> aeroportEntities);

}
