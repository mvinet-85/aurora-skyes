package com.esiea.auroraskyesback.authentification.dto;

import lombok.Data;

@Data
public class AuthentificationDTO {

    /** Email de l'utilisateur */
    private String email;

    /** Mot de passe de l'utilisateur */
    private String motDePasse;

}
