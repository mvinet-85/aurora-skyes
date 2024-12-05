package com.esiea.auroraskyesback.vol.service;

import com.esiea.auroraskyesback.vol.dao.VolDAO;
import com.esiea.auroraskyesback.vol.entity.VolEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolServiceImpl implements VolService {

    /** {@link VolDAO} */
    private final VolDAO volDAO;

    public VolServiceImpl(VolDAO volDAO) {
        this.volDAO = volDAO;
    }

    /** {@inheritDoc} */
    public List<VolEntity> getAllVols() {
        return this.volDAO.findAll();
    }

    /** {@inheritDoc} */
    public VolEntity findVolById(Long id) {
        return this.volDAO.findById(id).orElseThrow(() -> new RuntimeException("Vol introuvable"));
    }

    /** {@inheritDoc} */
    public void modifierVol(VolEntity vol) {
        this.volDAO.save(vol);
    }

}

