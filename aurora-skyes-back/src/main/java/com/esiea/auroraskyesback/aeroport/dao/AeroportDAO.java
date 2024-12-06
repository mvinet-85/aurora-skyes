package com.esiea.auroraskyesback.aeroport.dao;

import com.esiea.auroraskyesback.aeroport.entity.AeroportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AeroportDAO extends JpaRepository<AeroportEntity, Long> {

    Optional<AeroportEntity> findByNom(String name);

}
