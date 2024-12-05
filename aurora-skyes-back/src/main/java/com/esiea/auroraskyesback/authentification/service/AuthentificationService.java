package com.esiea.auroraskyesback.authentification.service;

public interface AuthentificationService {

    /**
     * Méthode permettant de hasher le mot de passe
     * @param motDePasse mot de passe
     * @return le mot de passe hasher
     */
    String hashMotDePasse(String motDePasse);

    /**
     * Vérifie si le mot de passe correspond au mot de passe hasher
     * @param motDePasse en clair
     * @param motDePasseHash hasher
     * @return true si il y a correspondance
     */
    boolean verifierMotDePasse(String motDePasse, String motDePasseHash);

}
