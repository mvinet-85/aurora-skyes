package com.esiea.auroraskyesback.authentification.service;

import com.esiea.auroraskyesback.authentification.exception.InvalidPasswordException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthentificationServiceImpl implements AuthentificationService {

    /** {@link BCryptPasswordEncoder} */
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthentificationServiceImpl() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /** {@inheritDoc} */
    public String hashMotDePasse(String motDePasse) {
        if (motDePasse == null || motDePasse.isEmpty()) {
            throw new InvalidPasswordException("Le mot de passe fourni est invalide");
        }
        return passwordEncoder.encode(motDePasse);
    }

}
