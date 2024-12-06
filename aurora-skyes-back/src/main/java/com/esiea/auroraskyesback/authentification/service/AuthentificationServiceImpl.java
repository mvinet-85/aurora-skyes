package com.esiea.auroraskyesback.authentification.service;

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
        return passwordEncoder.encode(motDePasse);
    }

    /** {@inheritDoc} */
    public boolean verifierMotDePasse(String motDePasse, String motDePasseHash) {
        return passwordEncoder.matches(motDePasse, motDePasseHash);
    }
}
