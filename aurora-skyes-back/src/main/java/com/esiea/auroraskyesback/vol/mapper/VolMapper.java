package com.esiea.auroraskyesback.vol.mapper;

import com.esiea.auroraskyesback.vol.dto.VolDTO;
import com.esiea.auroraskyesback.vol.entity.VolEntity;
import com.esiea.auroraskyesback.aeroport.mapper.AeroportMapper;
import org.springframework.stereotype.Component;

@Component
public class VolMapper {

    /** {@link AeroportMapper} */
    private final AeroportMapper aeroportMapper;

    public VolMapper(AeroportMapper aeroportMapper) {
        this.aeroportMapper = aeroportMapper;
    }

    public VolDTO toDTO(VolEntity volEntity) {
        if (volEntity == null) {
            return null;
        }

        VolDTO volDTO = new VolDTO();
        volDTO.setId(volEntity.getId());
        volDTO.setDateDepart(volEntity.getDateDepart());
        volDTO.setDateArrive(volEntity.getDateArrive());
        volDTO.setPlaceDisponible(volEntity.getPlaceDisponible());
        volDTO.setAeroportDepart(aeroportMapper.toDTO(volEntity.getAeroportDepart()));
        volDTO.setAeroportArrivee(aeroportMapper.toDTO(volEntity.getAeroportArrivee()));
        volDTO.setPrix(volEntity.getPrix());

        return volDTO;
    }

    public VolEntity toEntity(VolDTO volDTO) {
        if (volDTO == null) {
            return null;
        }

        VolEntity volEntity = new VolEntity();
        volEntity.setDateDepart(volDTO.getDateDepart());
        volEntity.setDateArrive(volDTO.getDateArrive());
        volEntity.setPlaceDisponible(volDTO.getPlaceDisponible());
        volEntity.setAeroportDepart(aeroportMapper.toEntity(volDTO.getAeroportDepart()));
        volEntity.setAeroportArrivee(aeroportMapper.toEntity(volDTO.getAeroportArrivee()));

        return volEntity;
    }

}

