package com.esiea.auroraskyesback.monnaie.dao;

import com.esiea.auroraskyesback.monnaie.entity.MonnaieEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonnaieDAO extends JpaRepository<MonnaieEntity, Long> {

    Optional<MonnaieEntity> findByNom(String nom);
    
}
