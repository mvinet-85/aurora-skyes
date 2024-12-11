package com.esiea.auroraskyesdbaccess.aeroport.service;

import com.esiea.auroraskyesdbaccess.aeroport.dao.AeroportDAO;
import com.esiea.auroraskyesdbaccess.aeroport.entity.AeroportEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AeroportServiceImpl implements AeroportService {

	/** {@link AeroportDAO} */
	private final AeroportDAO aeroportDAO;

	public AeroportServiceImpl(AeroportDAO aeroportDAO) {
		this.aeroportDAO = aeroportDAO;
	}

	/** {@inheritDoc} */
	@Override
	public List<AeroportEntity> getAllAeroports() {
		return this.aeroportDAO.findAll();
	}
	
}
