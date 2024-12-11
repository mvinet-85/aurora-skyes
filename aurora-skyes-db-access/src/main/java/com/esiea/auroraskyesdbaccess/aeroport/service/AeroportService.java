package com.esiea.auroraskyesdbaccess.aeroport.service;

import com.esiea.auroraskyesdbaccess.aeroport.entity.AeroportEntity;

import java.util.List;

public interface AeroportService {

    /**
     * Retourne la liste des aéroports
     * @return liste d'aéroport
     */
    List<AeroportEntity> getAllAeroports();

}
