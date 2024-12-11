package com.esiea.auroraskyesdbaccess.authentification.controller;

import com.esiea.auroraskyesdbaccess.authentification.dto.AuthentificationDTO;
import com.esiea.auroraskyesdbaccess.authentification.dto.ResponseDTO;
import com.esiea.auroraskyesdbaccess.authentification.utils.JwtUtil;
import com.esiea.auroraskyesdbaccess.utilisateur.service.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentification")
public class AuthentificationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthentificationController.class);

    /** {@link AuthenticationManager} */
    private final AuthenticationManager authenticationManager;

    /** {@link UtilisateurService} */
    private final UtilisateurService utilisateurService;

    /** {@link JwtUtil} */
    private final JwtUtil jwtUtil;

    public AuthentificationController(AuthenticationManager authenticationManager,
                                      JwtUtil jwtUtil, UtilisateurService utilisateurService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.utilisateurService = utilisateurService;
    }

    /**
     * Endpoint permettant de s'authentifier
     *
     * @param authentificationDTO informations de connexion
     * @return boolean qui indique l'authentification
     */
    @PostMapping()
    public ResponseEntity<ResponseDTO> login(@RequestBody AuthentificationDTO authentificationDTO) {
        LOGGER.info("Tentative d'authentification pour l'email : {}", authentificationDTO.getEmail());

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authentificationDTO.getEmail(),
                            authentificationDTO.getMotDePasse()
                    )
            );

            LOGGER.info("Authentification réussie pour l'email : {}", authentificationDTO.getEmail());

            ResponseDTO responseDTO = new ResponseDTO();
            String token = jwtUtil.generateToken(authentication.getName());
            responseDTO.setToken(token);
            LOGGER.info("Token JWT généré avec succès pour l'utilisateur : {}", authentificationDTO.getEmail());
            Long userId = this.utilisateurService.findUtilisateurByEmail(authentificationDTO.getEmail()).getId();
            responseDTO.setId(userId);

            return ResponseEntity.ok(responseDTO);

        } catch (BadCredentialsException e) {
            LOGGER.error("Échec de l'authentification pour l'email : {}. Raison : Identifiants invalides", authentificationDTO.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (Exception e) {
            LOGGER.error("Erreur inattendue lors de l'authentification pour l'email : {}. Détails : {}", authentificationDTO.getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
}
