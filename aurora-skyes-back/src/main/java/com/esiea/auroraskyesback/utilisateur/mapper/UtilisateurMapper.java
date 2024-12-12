package com.esiea.auroraskyesback.utilisateur.mapper;

import com.esiea.auroraskyesback.utilisateur.dto.UtilisateurDTO;
import com.esiea.auroraskyesdbaccess.utilisateur.dto.UtilisateurBDDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {

    UtilisateurBDDTO utilisateurDTOToUtilisateurBDDTO(UtilisateurDTO utilisateurDTO);

    UtilisateurDTO utilisateurBDDTOToUtilisateurDTO(UtilisateurBDDTO utilisateurBDDTO);

}
