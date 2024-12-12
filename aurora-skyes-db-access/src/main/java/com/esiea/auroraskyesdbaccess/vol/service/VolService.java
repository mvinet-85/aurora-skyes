package com.esiea.auroraskyesdbaccess.vol.service;

import com.esiea.auroraskyesdbaccess.aeroport.dto.AeroportBDDTO;
import com.esiea.auroraskyesdbaccess.aeroport.entity.AeroportExternalEntity;
import com.esiea.auroraskyesdbaccess.vol.dto.VolBDDTO;
import com.esiea.auroraskyesdbaccess.vol.entity.VolEntity;
import com.esiea.auroraskyesdbaccess.vol.entity.VolExternalEntity;

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

    /**
     * Créer un vol avec les informations via l'api
     *
     * @param volBDDTO informations du vol
     * @return vol créé
     */
    VolExternalEntity createVolExternal(VolBDDTO volBDDTO);
}
