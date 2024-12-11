package com.esiea.auroraskyesdbaccess.utilisateur.service;

import com.esiea.auroraskyesdbaccess.utilisateur.dao.UtilisateurDAO;
import com.esiea.auroraskyesdbaccess.utilisateur.dto.UtilisateurBDDTO;
import com.esiea.auroraskyesdbaccess.utilisateur.entity.UtilisateurEntity;
import com.esiea.auroraskyesdbaccess.utilisateur.exception.InvalidUtilisateurException;
import com.esiea.auroraskyesdbaccess.utilisateur.exception.UtilisateurNotFoundException;
import com.esiea.auroraskyesdbaccess.utilisateur.mapper.UtilisateurMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class UtilisateurServiceImpl implements UserDetailsService, UtilisateurService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UtilisateurServiceImpl.class);

    /** {@link UtilisateurDAO} */
    private final UtilisateurDAO utilisateurDAO;

    /** {@link UtilisateurMapper} */
    private final UtilisateurMapper utilisateurMapper;

    public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO,
                              UtilisateurMapper utilisateurMapper) {
        this.utilisateurDAO = utilisateurDAO;
        this.utilisateurMapper = utilisateurMapper;
    }

    /** {@inheritDoc} */
    @Transactional
    public UtilisateurBDDTO creerUtilisateur(UtilisateurBDDTO utilisateurDTO) {
        if (utilisateurDTO.getEmail() == null || utilisateurDTO.getMotDePasse() == null) {
            LOGGER.error("Les champs email et mot de passe sont obligatoires");
            throw new InvalidUtilisateurException("Les champs email et mot de passe sont obligatoires");
        }
        UtilisateurEntity utilisateur = this.utilisateurMapper.utilisateurBDDTOToUtilisateurEntity(utilisateurDTO);
        UtilisateurEntity savedUser = utilisateurDAO.save(utilisateur);

        return this.utilisateurMapper.utilisateurEntityToUtilisateurBDDTO(savedUser);
    }

    /** {@inheritDoc} */
    public UtilisateurEntity findUtilisateurByEmail(String email) {
        return this.utilisateurDAO.findByEmail(email)
                .orElseThrow(() -> {
                    LOGGER.error("Utilisateur avec l'email " + email + " introuvable");
                    return new UtilisateurNotFoundException("Utilisateur avec l'email " + email + " introuvable");
                });
    }

    public UtilisateurEntity findUtilisateurById(Long id) {
        return this.utilisateurDAO.findById(id)
                .orElseThrow(() -> {
                    LOGGER.error("Utilisateur avec l'ID " + id + " introuvable");
                    return new UtilisateurNotFoundException("Utilisateur avec l'ID " + id + " introuvable");
                });
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UtilisateurEntity utilisateur = this.utilisateurDAO.findByEmail(email)
                .orElseThrow(() -> {
                    LOGGER.error("Utilisateur avec l'email " + email + " introuvable");
                    return new UtilisateurNotFoundException("Utilisateur avec l'email " + email + " introuvable");
                });

        return new User(utilisateur.getEmail(), utilisateur.getMotDePasse(), new ArrayList<>());

    }

}
