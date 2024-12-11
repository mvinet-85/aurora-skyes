package com.esiea.auroraskyesback.aeroport.service;

import com.esiea.auroraskyesdbaccess.aeroport.dto.AeroportBDDTO;

import java.util.List;

public interface AeroportService {

    /**
     * Retourne la liste des aéroports
     * @return liste d'aéroport
     */
    List<AeroportBDDTO> getAllAeroports();

}
