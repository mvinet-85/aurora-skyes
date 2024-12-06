package com.esiea.auroraskyesback.authentification.controller;

import com.esiea.auroraskyesback.authentification.dto.AuthentificationDTO;
import com.esiea.auroraskyesback.authentification.utils.JwtUtil;
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

    /** {@link JwtUtil} */
    private final JwtUtil jwtUtil;

    public AuthentificationController(AuthenticationManager authenticationManager,
                                      JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Endpoint permettant de s'authentifier
     *
     * @param authentificationDTO informations de connexion
     * @return boolean qui indique l'authentification
     */
    @PostMapping()
    public ResponseEntity<?> login(@RequestBody AuthentificationDTO authentificationDTO) {
        LOGGER.info("Tentative d'authentification pour l'email : {}", authentificationDTO.getEmail());

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authentificationDTO.getEmail(),
                            authentificationDTO.getMotDePasse()
                    )
            );

            LOGGER.info("Authentification réussie pour l'email : {}", authentificationDTO.getEmail());

            String token = jwtUtil.generateToken(authentication.getName());
            LOGGER.info("Token JWT généré avec succès pour l'utilisateur : {}", authentificationDTO.getEmail());

            return ResponseEntity.ok(token);

        } catch (BadCredentialsException e) {
            LOGGER.error("Échec de l'authentification pour l'email : {}. Raison : Identifiants invalides", authentificationDTO.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Identifiants invalides");
        } catch (Exception e) {
            LOGGER.error("Erreur inattendue lors de l'authentification pour l'email : {}. Détails : {}", authentificationDTO.getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur serveur");
        }
    }
    
}
