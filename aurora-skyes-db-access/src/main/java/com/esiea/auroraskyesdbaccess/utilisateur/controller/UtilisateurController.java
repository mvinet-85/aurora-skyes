package com.esiea.auroraskyesdbaccess.utilisateur.controller;

import com.esiea.auroraskyesdbaccess.utilisateur.dto.UtilisateurBDDTO;
import com.esiea.auroraskyesdbaccess.utilisateur.mapper.UtilisateurMapper;
import com.esiea.auroraskyesdbaccess.utilisateur.service.UtilisateurService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    /** {@link UtilisateurService} */
    private final UtilisateurService utilisateurService;

    /** {@link UtilisateurMapper} */
    private final UtilisateurMapper utilisateurMapper;

    public UtilisateurController(UtilisateurService utilisateurService,
                                 UtilisateurMapper utilisateurMapper) {
        this.utilisateurService = utilisateurService;
        this.utilisateurMapper = utilisateurMapper;
    }

    /**
     * Permet de créer un utilisateur
     * @param utilisateurDTO informations de l'utilisateur
     * @return l'utilisateur créé
     */
    @PostMapping
    public UtilisateurBDDTO creerUtilisateur(@RequestBody UtilisateurBDDTO utilisateurDTO) {
        return this.utilisateurService.creerUtilisateur(utilisateurDTO);
    }

    /**
     * Permet de créer un utilisateur
     * @param email de l'utilisateur
     * @return l'utilisateur trouvé
     */
    @GetMapping("/email/{email}")
    public UtilisateurBDDTO findUtilisateurByEmail(@PathVariable String email) {
        return this.utilisateurMapper.utilisateurEntityToUtilisateurBDDTO(this.utilisateurService.findUtilisateurByEmail(email));
    }

}