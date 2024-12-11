package com.esiea.auroraskyesback.vol.service;

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

}
