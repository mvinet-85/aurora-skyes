package com.esiea.auroraskyesdbaccess.vol.service;

import com.esiea.auroraskyesdbaccess.aeroport.dto.AeroportBDDTO;
import com.esiea.auroraskyesdbaccess.aeroport.entity.AeroportExternalEntity;
import com.esiea.auroraskyesdbaccess.vol.dao.VolDAO;
import com.esiea.auroraskyesdbaccess.vol.dao.VolExternalDAO;
import com.esiea.auroraskyesdbaccess.vol.dto.VolBDDTO;
import com.esiea.auroraskyesdbaccess.vol.entity.VolEntity;
import com.esiea.auroraskyesdbaccess.vol.entity.VolExternalEntity;
import com.esiea.auroraskyesdbaccess.vol.exception.VolNotFoundException;
import com.esiea.auroraskyesdbaccess.vol.exception.InvalidVolException;
import com.esiea.auroraskyesdbaccess.vol.mapper.VolMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import io.micrometer.core.instrument.Gauge;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VolServiceImpl implements VolService {

	private static final Logger LOGGER = LoggerFactory.getLogger(VolServiceImpl.class);

	/** {@link VolDAO} */
	private final VolDAO volDAO;

	/** {@link VolExternalDAO} */
	private final VolExternalDAO volExternalDAO;

	/** {@link VolMapper} */
	private final VolMapper volMapper;

	/** {@link MeterRegistry} */
	private final MeterRegistry meterRegistry;

	public VolServiceImpl(VolDAO volDAO,
						  MeterRegistry meterRegistry,
						  VolExternalDAO volExternalDAO,
						  VolMapper volMapper) {
		this.volDAO = volDAO;
		this.meterRegistry = meterRegistry;
		this.volExternalDAO = volExternalDAO;
		this.volMapper = volMapper;
	}

	@PostConstruct
	public void initializeMetrics() {
		this.volDAO.findAll().forEach(vol -> {
			Gauge.builder("vol.places_disponibles", () -> {
				VolEntity updatedVol = volDAO.findById(vol.getId()).orElse(null);
				return updatedVol != null ? updatedVol.getPlaceDisponible() : 0;
			})
			.description("Nombre de places disponibles pour chaque vol")
			.tags("volId", String.valueOf(vol.getId()))
			.register(meterRegistry);
		});
	}

	/** {@inheritDoc} */
	public List<VolEntity> getAllVols() {
		return this.volDAO.findAll();
	}

	/** {@inheritDoc} */
	public VolEntity findVolById(Long id) {
		return this.volDAO.findById(id)
				.orElseThrow(() -> {
					LOGGER.error("Vol avec l'ID " + id + " introuvable");
					return new VolNotFoundException("Vol avec l'ID " + id + " introuvable");
				});
	}

	/** {@inheritDoc} */
	public void modifierVol(VolEntity vol) {
		if (vol == null || vol.getId() == null) {
			LOGGER.error("Le vol est invalide ou ne possède pas d'ID");
			throw new InvalidVolException("Le vol est invalide ou ne possède pas d'ID");
		}
		this.volDAO.save(vol);
	}

	/** {@inheritDoc} */
	@Transactional
	public VolExternalEntity createVolExternal(VolBDDTO volBDDTO) {
		return this.volExternalDAO.save(this.volMapper.volBDDTOToVolExternalEntity(volBDDTO));
	}

}
