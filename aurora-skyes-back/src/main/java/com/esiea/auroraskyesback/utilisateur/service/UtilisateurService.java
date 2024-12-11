package com.esiea.auroraskyesback.utilisateur.service;

import com.esiea.auroraskyesback.utilisateur.dto.UtilisateurDTO;
import com.esiea.auroraskyesdbaccess.utilisateur.dto.UtilisateurBDDTO;

public interface UtilisateurService {

    /**
     * Creer un utilisateur
     * @param utilisateurDTO informations de l'utilisateur
     * @return l'utilisateur créer
     */
    UtilisateurBDDTO creerUtilisateur(UtilisateurDTO utilisateurDTO);

}
