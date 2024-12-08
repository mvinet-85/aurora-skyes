package com.esiea.auroraskyesback.vol.service;

import com.esiea.auroraskyesback.vol.dao.VolDAO;
import com.esiea.auroraskyesback.vol.entity.VolEntity;
import org.springframework.stereotype.Service;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import io.micrometer.core.instrument.Gauge;

import java.util.List;

@Service
public class VolServiceImpl implements VolService {

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
		return this.volDAO.findById(id).orElseThrow(() -> new RuntimeException("Vol introuvable"));
	}

	/** {@inheritDoc} */
	public void modifierVol(VolEntity vol) {
		this.volDAO.save(vol);
	}

}
