package com.esiea.auroraskyesdbaccess.utilisateur.mapper;

import com.esiea.auroraskyesdbaccess.utilisateur.dto.UtilisateurBDDTO;
import com.esiea.auroraskyesdbaccess.utilisateur.entity.UtilisateurEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {

    UtilisateurEntity utilisateurBDDTOToUtilisateurEntity(UtilisateurBDDTO utilisateurBDDTO);

    UtilisateurBDDTO utilisateurEntityToUtilisateurBDDTO(UtilisateurEntity utilisateurEntity);

}