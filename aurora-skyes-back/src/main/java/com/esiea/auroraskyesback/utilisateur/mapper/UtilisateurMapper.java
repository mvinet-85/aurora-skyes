package com.esiea.auroraskyesback.utilisateur.mapper;

import com.esiea.auroraskyesback.utilisateur.dto.UtilisateurDTO;
import com.esiea.auroraskyesback.utilisateur.entity.UtilisateurEntity;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurMapper {

    public UtilisateurEntity toEntity(UtilisateurDTO utilisateurDTO) {
        if (utilisateurDTO == null) {
            return null;
        }
        UtilisateurEntity utilisateurEntity = new UtilisateurEntity();
        utilisateurEntity.setNom(utilisateurDTO.getNom());
        utilisateurEntity.setEmail(utilisateurDTO.getEmail());
        utilisateurEntity.setMotDePasse(utilisateurDTO.getMotDePasse());
        return utilisateurEntity;
    }

    public UtilisateurDTO toDTO(UtilisateurEntity utilisateurEntity) {
        if (utilisateurEntity == null) {
            return null;
        }
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setId(utilisateurEntity.getId());
        utilisateurDTO.setNom(utilisateurEntity.getNom());
        utilisateurDTO.setEmail(utilisateurEntity.getEmail());
        return utilisateurDTO;
    }

}
