package com.esiea.auroraskyesback.aeroport.service;

import com.esiea.auroraskyesback.aeroport.dto.AeroportDTO;

// import com.esiea.auroraskyesback.exception.controller.exception.ExternalApiException;
import com.esiea.auroraskyesback.aeroport.entity.AeroportEntity;
import org.springframework.stereotype.Service;
// import org.springframework.web.client.RestTemplate;
// import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class AeroportServiceImpl implements AeroportService {

	
	// private final RestTemplate restTemplate;

	// public AeroportServiceImpl(RestTemplate restTemplate) {
    //     this.restTemplate = restTemplate;
    // }

	/**
	 * Appelle l'API externe pour récupérer la liste des aéroports.
	 *
	 * @return liste des AeroportDTO
	 */
	public List<AeroportEntity> getAllAeroports() {
		return null;
		
		// String apiUrl = "http://localhost:8080/aeroports";

		// String fullUrl = UriComponentsBuilder.fromUriString(apiUrl)
		// 		.build()
		// 		.toUriString();

		// try {
		// 	AeroportDTO[] aeroportArray = null; // restTemplate.getForObject(fullUrl, AeroportBDDTO[].class);
		// 	return Arrays.asList(aeroportArray);
		// } catch (Exception e) {
		// 	throw new ExternalApiException("Erreur lors de l'appel à l'API externe", e);
		// }

	}

}
