package com.esiea.auroraskyesdbaccess.utilisateur.service;

import com.esiea.auroraskyesdbaccess.utilisateur.dto.UtilisateurBDDTO;
import com.esiea.auroraskyesdbaccess.utilisateur.entity.UtilisateurEntity;

public interface UtilisateurService {

    /**
     * Creer un utilisateur
     * @param utilisateurDTO informations de l'utilisateur
     * @return l'utilisateur créer
     */
    UtilisateurBDDTO creerUtilisateur(UtilisateurBDDTO utilisateurDTO);

    /**
     * Cherche un utilisateur par son email
     * @param email de l'utilisateur
     * @return l'utilisateur trouvé
     */
    UtilisateurEntity findUtilisateurByEmail(String email);

    /**
     * Cherche un utilisateur par son id
     * @param id de l'utilisateur
     * @return l'utilisateur trouvé
     */
    UtilisateurEntity findUtilisateurById(Long id);

}
