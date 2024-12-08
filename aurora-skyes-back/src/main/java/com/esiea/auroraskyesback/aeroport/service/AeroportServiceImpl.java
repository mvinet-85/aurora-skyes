package com.esiea.auroraskyesback.aeroport.service;

import com.esiea.auroraskyesback.aeroport.dao.AeroportDAO;
import com.esiea.auroraskyesback.aeroport.entity.AeroportEntity;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AeroportServiceImpl implements AeroportService {

	/** {@link AeroportDAO} */
	private final AeroportDAO aeroportDAO;

	/** Counter pour suivre le nombre d'appels */
	private final Counter aeroportCounter;

	/** Timer pour mesurer la durée des appels */
	private final Timer aeroportTimer;

	public AeroportServiceImpl(AeroportDAO aeroportDAO, MeterRegistry meterRegistry) {
		this.aeroportDAO = aeroportDAO;

		// Initialisation du compteur
		this.aeroportCounter = meterRegistry.counter("service.aeroports.calls");

		// Initialisation du timer
		this.aeroportTimer = meterRegistry.timer("service.aeroports.execution.time");
	}

	/** {@inheritDoc} */
	@Override
	public List<AeroportEntity> getAllAeroports() {
		aeroportCounter.increment(); // Incrémente le compteur à chaque appel

		// Mesure la durée d'exécution de la méthode
		return aeroportTimer.record(() -> this.aeroportDAO.findAll());
	}
	
}
