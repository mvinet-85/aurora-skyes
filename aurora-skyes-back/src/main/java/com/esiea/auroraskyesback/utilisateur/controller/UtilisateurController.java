package com.esiea.auroraskyesback.utilisateur.controller;

import com.esiea.auroraskyesback.authentification.controller.AuthentificationController;
import com.esiea.auroraskyesback.authentification.dto.ResponseDTO;
import com.esiea.auroraskyesback.authentification.utils.JwtUtil;
import com.esiea.auroraskyesback.utilisateur.dto.UtilisateurDTO;
import com.esiea.auroraskyesback.utilisateur.mapper.UtilisateurMapper;
import com.esiea.auroraskyesback.utilisateur.service.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthentificationController.class);

    /** {@link UtilisateurService} */
    private final UtilisateurService utilisateurService;

    /** {@link UtilisateurMapper} */
    private final UtilisateurMapper utilisateurMapper;

    /** {@link JwtUtil} */
    private final JwtUtil jwtUtil;

    public UtilisateurController(UtilisateurService utilisateurService,
                                 JwtUtil jwtUtil,
                                 UtilisateurMapper utilisateurMapper) {
        this.utilisateurService = utilisateurService;
        this.jwtUtil = jwtUtil;
        this.utilisateurMapper = utilisateurMapper;
    }

    /**
     * Permet de créer un utilisateur
     * @param utilisateurDTO informations de l'utilisateur
     * @return l'utilisateur créé
     */
    @PostMapping
    public ResponseEntity<ResponseDTO> creerUtilisateur(@RequestBody UtilisateurDTO utilisateurDTO) {
        UtilisateurDTO utilisateur = this.utilisateurMapper.utilisateurBDDTOToUtilisateurDTO(utilisateurService.creerUtilisateur(utilisateurDTO));

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setId(utilisateur.getId());
        String token = jwtUtil.generateToken(utilisateur.getNom());
        responseDTO.setToken(token);
        LOGGER.info("Token JWT généré avec succès pour l'utilisateur : {}", utilisateur.getEmail());

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

}