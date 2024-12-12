package com.esiea.auroraskyesdbaccess.reservation.dto;

import com.esiea.auroraskyesdbaccess.reservation.model.Classe;
import com.esiea.auroraskyesdbaccess.utilisateur.dto.UtilisateurBDDTO;
import com.esiea.auroraskyesdbaccess.vol.dto.VolBDDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationBDDTO {

    /** Id de la reservation */
    private Long id;

    /** Utilisateur */
    private UtilisateurBDDTO user;

    /** Vol associé */
    private VolBDDTO vol;

    /** CLasse */
    private Classe classe;

    /** Siege */
    private String siege;

    /** Prix */
    private double prix;

}
