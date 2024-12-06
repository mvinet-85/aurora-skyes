package com.esiea.auroraskyesback.utilisateur.service;

import com.esiea.auroraskyesback.utilisateur.dto.UtilisateurDTO;
import com.esiea.auroraskyesback.utilisateur.entity.UtilisateurEntity;

public interface UtilisateurService {

    /**
     * Creer un utilisateur
     * @param utilisateurDTO informations de l'utilisateur
     * @return l'utilisateur créer
     */
    UtilisateurDTO creerUtilisateur(UtilisateurDTO utilisateurDTO);

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
