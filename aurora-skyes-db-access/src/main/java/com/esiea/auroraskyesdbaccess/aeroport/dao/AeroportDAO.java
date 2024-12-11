package com.esiea.auroraskyesdbaccess.aeroport.dao;

import com.esiea.auroraskyesdbaccess.aeroport.entity.AeroportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AeroportDAO extends JpaRepository<AeroportEntity, Long> {

}
