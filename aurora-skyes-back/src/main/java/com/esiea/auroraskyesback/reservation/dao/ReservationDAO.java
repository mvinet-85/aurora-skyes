package com.esiea.auroraskyesback.reservation.dao;

import com.esiea.auroraskyesback.reservation.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationDAO extends JpaRepository<ReservationEntity, Long> {

}
