package com.esiea.auroraskyesback.monnaie.mapper;

import com.esiea.auroraskyesback.monnaie.dto.MonnaieDTO;
import com.esiea.auroraskyesdbaccess.monnaie.dto.MonnaieBDDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MonnaieMapper {

    List<MonnaieDTO> monnaieBDDTOSToMonnaieDTO(List<MonnaieBDDTO> monnaieBDDTOS);

}
