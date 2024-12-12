package com.esiea.auroraskyesdbaccess.aeroport.dao;

import com.esiea.auroraskyesdbaccess.aeroport.entity.AeroportExternalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AeroportExternalDAO extends JpaRepository<AeroportExternalEntity, Long> {

}
