package com.esiea.auroraskyesback.authentification.service;

public interface AuthentificationService {

    /**
     * Méthode permettant de hasher le mot de passe
     * @param motDePasse mot de passe
     * @return le mot de passe hasher
     */
    String hashMotDePasse(String motDePasse);

}
