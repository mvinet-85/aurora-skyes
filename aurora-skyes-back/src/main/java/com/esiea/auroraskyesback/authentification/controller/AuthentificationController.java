package com.esiea.auroraskyesback.authentification.controller;

import com.esiea.auroraskyesback.authentification.dto.AuthentificationDTO;
import com.esiea.auroraskyesback.authentification.service.AuthentificationService;
import com.esiea.auroraskyesback.utilisateur.entity.UtilisateurEntity;
import com.esiea.auroraskyesback.utilisateur.service.UtilisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentification")
public class AuthentificationController {

    /** {@link AuthentificationService} */
    private final AuthentificationService authentificationService;

    /** {@link UtilisateurService} */
    private final UtilisateurService utilisateurService;

    public AuthentificationController(AuthentificationService authentificationService,
                                      UtilisateurService utilisateurService) {
        this.authentificationService = authentificationService;
        this.utilisateurService = utilisateurService;
    }

    /**
     * Endpoint permettant de s'authentifier
     * @param authentificationDTO informations de connexion
     * @return boolean qui indique l'authentification
     */
    @PostMapping
    public ResponseEntity<Boolean> verifierConnexion(@RequestBody AuthentificationDTO authentificationDTO) {

        UtilisateurEntity utilisateur = utilisateurService.findUtilisateurByEmail(authentificationDTO.getEmail());
        if (utilisateur == null) {
            return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
        }
        boolean motDePasseValide = this.authentificationService.verifierMotDePasse(authentificationDTO.getMotDePasse(), utilisateur.getMotDePasse());
        if (!motDePasseValide) {
            return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);

    }

}