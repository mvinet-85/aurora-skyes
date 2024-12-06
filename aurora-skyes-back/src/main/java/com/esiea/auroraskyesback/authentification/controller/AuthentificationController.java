package com.esiea.auroraskyesback.authentification.controller;

import com.esiea.auroraskyesback.authentification.dto.AuthentificationDTO;
import com.esiea.auroraskyesback.authentification.utils.JwtUtil;
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

    /** {@link AuthenticationManager} */
    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    public AuthentificationController(AuthenticationManager authenticationManager,
                                      JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Endpoint permettant de s'authentifier
     * @param authentificationDTO informations de connexion
     * @return boolean qui indique l'authentification
     */
    
    @PostMapping()
    public ResponseEntity<?> login(@RequestBody AuthentificationDTO authentificationDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authentificationDTO.getEmail(), authentificationDTO.getMotDePasse()));
            String token = jwtUtil.generateToken(authentication.getName());
            return ResponseEntity.ok(token);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

}