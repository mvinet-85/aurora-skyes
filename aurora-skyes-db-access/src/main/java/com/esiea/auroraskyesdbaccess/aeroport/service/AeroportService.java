package com.esiea.auroraskyesdbaccess.aeroport.service;

import com.esiea.auroraskyesdbaccess.aeroport.dto.AeroportBDDTO;
import com.esiea.auroraskyesdbaccess.aeroport.entity.AeroportEntity;
import com.esiea.auroraskyesdbaccess.aeroport.entity.AeroportExternalEntity;
import com.esiea.auroraskyesdbaccess.reservation.dto.ReservationBDDTO;
import com.esiea.auroraskyesdbaccess.reservation.entity.ReservationEntity;

import java.util.List;

public interface AeroportService {

    /**
     * Retourne la liste des aéroports
     * @return liste d'aéroport
     */
    List<AeroportEntity> getAllAeroports();

    /**
     * Créer un aeroport avec les informations
     *
     * @param aeroportBDDTO informations de l'aeroport
     * @return l aeroport créé
     */
    AeroportExternalEntity createAeroport(AeroportBDDTO aeroportBDDTO);

}
