package com.esiea.auroraskyesdbaccess.utilisateur.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
