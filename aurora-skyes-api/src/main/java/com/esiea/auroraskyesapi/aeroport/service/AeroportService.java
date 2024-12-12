package com.esiea.auroraskyesapi.aeroport.service;

import com.esiea.auroraskyesapi.aeroport.dto.AeroportGlobaleDTO;
import com.esiea.auroraskyesdbaccess.aeroport.dto.AeroportBDDTO;

import java.util.List;

public interface AeroportService {

    /**
     * Retourne la liste des aéroports
     * @return liste d'aéroport
     */
    List<AeroportBDDTO> getAllAeroports();

    /**
     * Creer un aeroport
     * @param aeroportGlobaleDTO information de l'aeroport
     * @return l'aeroport créé
     */
    AeroportBDDTO createAeroport(AeroportGlobaleDTO aeroportGlobaleDTO);

}
