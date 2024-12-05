package com.esiea.auroraskyesback.vol.dao;

import com.esiea.auroraskyesback.vol.entity.VolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolDAO extends JpaRepository<VolEntity, Long> {

}
