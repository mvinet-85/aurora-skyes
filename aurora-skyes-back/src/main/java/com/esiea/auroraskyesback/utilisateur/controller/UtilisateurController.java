package com.esiea.auroraskyesback.utilisateur.controller;

import com.esiea.auroraskyesback.utilisateur.dto.UtilisateurDTO;
import com.esiea.auroraskyesback.utilisateur.service.UtilisateurService;
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
    public ResponseEntity<UtilisateurDTO> creerUtilisateur(@RequestBody UtilisateurDTO utilisateurDTO) {
        UtilisateurDTO utilisateur = utilisateurService.creerUtilisateur(utilisateurDTO);
        return new ResponseEntity<>(utilisateur, HttpStatus.CREATED);
    }

}