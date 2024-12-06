package com.esiea.auroraskyesback.aeroport.mapper;

import com.esiea.auroraskyesback.aeroport.dto.AeroportDTO;
import com.esiea.auroraskyesback.aeroport.entity.AeroportEntity;
import org.springframework.stereotype.Component;

@Component
public class AeroportMapper {

    public AeroportEntity toEntity(AeroportDTO aeroportDTO) {
        if (aeroportDTO == null) {
            return null;
        }
        AeroportEntity aeroportEntity = new AeroportEntity();
        aeroportEntity.setNom(aeroportDTO.getNom());
        aeroportEntity.setVille(aeroportDTO.getVille());
        return aeroportEntity;
    }

    public AeroportDTO toDTO(AeroportEntity aeroportEntity) {
        if (aeroportEntity == null) {
            return null;
        }
        AeroportDTO aeroportDTO = new AeroportDTO();
        aeroportDTO.setId(aeroportEntity.getId());
        aeroportDTO.setNom(aeroportEntity.getNom());
        aeroportDTO.setVille(aeroportEntity.getVille());
        return aeroportDTO;
    }
}
