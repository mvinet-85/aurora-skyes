package com.esiea.auroraskyesback.utilisateur.service;

import com.esiea.auroraskyesback.authentification.service.AuthentificationService;
import com.esiea.auroraskyesback.utilisateur.dao.UtilisateurDAO;
import com.esiea.auroraskyesback.utilisateur.dto.UtilisateurDTO;
import com.esiea.auroraskyesback.utilisateur.entity.UtilisateurEntity;
import com.esiea.auroraskyesback.utilisateur.mapper.UtilisateurMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class UtilisateurServiceImpl implements UserDetailsService, UtilisateurService {

    /** {@link UtilisateurDAO} */
    private final UtilisateurDAO utilisateurDAO;

    /** {@link UtilisateurMapper} */
    private final UtilisateurMapper utilisateurMapper;

    /** {@link AuthentificationService} */
    private final AuthentificationService authentificationService;

    public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO,
                              UtilisateurMapper utilisateurMapper,
                              AuthentificationService authentificationService) {
        this.utilisateurDAO = utilisateurDAO;
        this.utilisateurMapper = utilisateurMapper;
        this.authentificationService = authentificationService;
    }

    /** {@inheritDoc} */
    @Transactional
    public UtilisateurDTO creerUtilisateur(UtilisateurDTO utilisateurDTO) {

        UtilisateurEntity utilisateur = this.utilisateurMapper.toEntity(utilisateurDTO);
        utilisateur.setMotDePasse(this.authentificationService.hashMotDePasse(utilisateurDTO.getMotDePasse()));
        UtilisateurEntity savedUser = utilisateurDAO.save(utilisateur);

        return this.utilisateurMapper.toDTO(savedUser);
    }

    /** {@inheritDoc} */
    public UtilisateurEntity findUtilisateurByEmail(String email) {
        return this.utilisateurDAO.findByEmail(email).orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
    }

    public UtilisateurEntity findUtilisateurById(Long id) {
        return this.utilisateurDAO.findById(id).orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UtilisateurEntity utilisateur = this.utilisateurDAO.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

        return new User(utilisateur.getEmail(), utilisateur.getMotDePasse(), new ArrayList<>());

    }

}
