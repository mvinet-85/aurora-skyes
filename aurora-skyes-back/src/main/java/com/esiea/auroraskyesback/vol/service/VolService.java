package com.esiea.auroraskyesback.vol.service;

import com.esiea.auroraskyesback.vol.entity.VolEntity;

import java.util.List;

public interface VolService {

    /**
     * Retourne la liste des vols
     * @return liste des vols
     */
    List<VolEntity> getAllVols();

    /**
     * Recherche un vol par son id
     * @param id du vol
     * @return le vol
     */
    VolEntity findVolById(Long id);

    /**
     * Modifie un vol
     * @param vol informations du vol
     * @return le vol modifié
     */
    void modifierVol(VolEntity vol);
}
