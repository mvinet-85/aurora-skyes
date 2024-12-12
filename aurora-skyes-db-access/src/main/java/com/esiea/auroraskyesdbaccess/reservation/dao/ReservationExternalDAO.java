package com.esiea.auroraskyesdbaccess.reservation.dao;

import com.esiea.auroraskyesdbaccess.reservation.entity.ReservationExternalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationExternalDAO extends JpaRepository<ReservationExternalEntity, Long> {

}
