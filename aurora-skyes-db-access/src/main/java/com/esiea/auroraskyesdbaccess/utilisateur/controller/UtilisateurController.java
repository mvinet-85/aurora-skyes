package com.esiea.auroraskyesdbaccess.utilisateur.controller;

import com.esiea.auroraskyesdbaccess.utilisateur.dto.UtilisateurBDDTO;
import com.esiea.auroraskyesdbaccess.utilisateur.service.UtilisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    /** {@link UtilisateurService} */
    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    /**
     * Permet de créer un utilisateur
     * @param utilisateurDTO informations de l'utilisateur
     * @return l'utilisateur créé
     */
    @PostMapping
    public ResponseEntity<?> creerUtilisateur(@RequestBody UtilisateurBDDTO utilisateurDTO) {
        UtilisateurBDDTO utilisateur = utilisateurService.creerUtilisateur(utilisateurDTO);

        return new ResponseEntity<>(utilisateur.getId(), HttpStatus.CREATED);
    }

}