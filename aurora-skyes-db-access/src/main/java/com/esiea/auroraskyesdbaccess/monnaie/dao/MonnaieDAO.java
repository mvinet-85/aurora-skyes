package com.esiea.auroraskyesdbaccess.monnaie.dao;

import com.esiea.auroraskyesdbaccess.monnaie.entity.MonnaieEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonnaieDAO extends JpaRepository<MonnaieEntity, Long> {

    Optional<MonnaieEntity> findByNom(String nom);
    
}
