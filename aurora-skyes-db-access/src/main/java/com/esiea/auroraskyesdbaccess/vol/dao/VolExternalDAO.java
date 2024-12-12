package com.esiea.auroraskyesdbaccess.vol.dao;

import com.esiea.auroraskyesdbaccess.vol.entity.VolExternalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolExternalDAO extends JpaRepository<VolExternalEntity, Long> {

}
