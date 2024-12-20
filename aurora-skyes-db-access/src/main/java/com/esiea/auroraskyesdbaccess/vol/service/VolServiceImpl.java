package com.esiea.auroraskyesdbaccess.vol.service;

import com.esiea.auroraskyesdbaccess.vol.dao.VolDAO;
import com.esiea.auroraskyesdbaccess.vol.entity.VolEntity;
import com.esiea.auroraskyesdbaccess.vol.exception.VolNotFoundException;
import com.esiea.auroraskyesdbaccess.vol.exception.InvalidVolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import io.micrometer.core.instrument.Gauge;

import java.util.List;

@Service
public class VolServiceImpl implements VolService {
	private static final Logger LOGGER = LoggerFactory.getLogger(VolServiceImpl.class);

	/** {@link VolDAO} */
	private final VolDAO volDAO;

	/** {@link MeterRegistry} */
	private final MeterRegistry meterRegistry;

	public VolServiceImpl(VolDAO volDAO,
			MeterRegistry meterRegistry) {
		this.volDAO = volDAO;
		this.meterRegistry = meterRegistry;
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

}
