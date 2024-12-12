package com.esiea.auroraskyesapi.vol.service;

import com.esiea.auroraskyesapi.vol.dto.VolGlobalDTO;
import com.esiea.auroraskyesdbaccess.vol.dto.VolBDDTO;

import java.util.List;

public interface VolService {

    /**
     * Retourne la liste des vols
     * @return liste des vols
     */
    List<VolBDDTO> getAllVols();

    /**
     * Recherche un vol par son id
     * @param id du vol
     * @return le vol
     */
    VolBDDTO findVolById(Long id);

    /**
     * Creer un vol
     * @param volGlobalDTO information du vol
     * @return le vol créé
     */
    VolBDDTO createVol(VolGlobalDTO volGlobalDTO);

}