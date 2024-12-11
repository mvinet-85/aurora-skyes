package com.esiea.auroraskyesback.aeroport.service;

import java.util.List;

import com.esiea.auroraskyesback.aeroport.dto.AeroportDTO;
import com.esiea.auroraskyesback.aeroport.entity.AeroportEntity;

public interface AeroportService {

    /**
     * Retourne la liste des aéroports
     * @return liste d'aéroport
     */
    List<AeroportEntity> getAllAeroports();

}
