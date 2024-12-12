package com.esiea.auroraskyesdbaccess.aeroport.service;

import com.esiea.auroraskyesdbaccess.aeroport.dao.AeroportDAO;
import com.esiea.auroraskyesdbaccess.aeroport.dao.AeroportExternalDAO;
import com.esiea.auroraskyesdbaccess.aeroport.dto.AeroportBDDTO;
import com.esiea.auroraskyesdbaccess.aeroport.entity.AeroportEntity;
import com.esiea.auroraskyesdbaccess.aeroport.entity.AeroportExternalEntity;
import com.esiea.auroraskyesdbaccess.aeroport.mapper.AeroportMapper;
import com.esiea.auroraskyesdbaccess.reservation.dto.ReservationBDDTO;
import com.esiea.auroraskyesdbaccess.reservation.entity.ReservationEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AeroportServiceImpl implements AeroportService {

	/** {@link AeroportDAO} */
	private final AeroportDAO aeroportDAO;

	/** {@link AeroportExternalDAO} */
	private final AeroportExternalDAO aeroportExternalDAO;

	/** {@link AeroportDAO} */
	private final AeroportMapper aeroportMapper;

	public AeroportServiceImpl(AeroportDAO aeroportDAO,
							   AeroportMapper aeroportMapper,
							   AeroportExternalDAO aeroportExternalDAO) {
		this.aeroportDAO = aeroportDAO;
		this.aeroportMapper = aeroportMapper;
		this.aeroportExternalDAO = aeroportExternalDAO;
	}

	/** {@inheritDoc} */
	@Override
	public List<AeroportEntity> getAllAeroports() {
		return this.aeroportDAO.findAll();
	}

	/** {@inheritDoc} */
	@Transactional
	public AeroportExternalEntity createAeroport(AeroportBDDTO aeroportBDDTO) {
		return this.aeroportExternalDAO.save(this.aeroportMapper.aeroportBDDTOToAeroportExternalEntity(aeroportBDDTO));
	}
	
}
