package com.esiea.auroraskyesback.utilisateur.dao;

import com.esiea.auroraskyesback.utilisateur.entity.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurDAO extends JpaRepository<UtilisateurEntity, Long> {

    /**
     * Recherche un utilisateur par son email
     * @param email de l'utilisateur
     * @return l'utilisateur trouvé
     */
    Optional<UtilisateurEntity> findByEmail(String email);

}
