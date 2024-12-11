package com.esiea.auroraskyesdbaccess.utilisateur.dto;

import lombok.Data;

@Data
public class UtilisateurBDDTO {

    /** Id de l'utilisateur */
    private Long id;

    /** Nom de l'utilisateur */
    private String nom;

    /** Email de l'utilisateur */
    private String email;

    /** Mot de passe de l'utilisateur */
    private String motDePasse;

}
