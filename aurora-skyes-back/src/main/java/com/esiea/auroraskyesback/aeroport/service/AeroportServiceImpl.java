package com.esiea.auroraskyesback.aeroport.service;

import com.esiea.auroraskyesback.aeroport.dao.AeroportDAO;
import com.esiea.auroraskyesback.aeroport.entity.AeroportEntity;
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
    public List<AeroportEntity> getAllAeroports() {
        return this.aeroportDAO.findAll();
    }

}

