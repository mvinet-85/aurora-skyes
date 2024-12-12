package com.esiea.auroraskyesback.monnaie.service;

import com.esiea.auroraskyesdbaccess.monnaie.dto.MonnaieBDDTO;

import java.util.List;

public interface MonnaieService {

    /**
     * Récupère toutes les monnaies
     *
     * @return la liste des monnaies
     */
    List<MonnaieBDDTO> getAllMonnaies();

}
