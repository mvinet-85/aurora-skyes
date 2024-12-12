package com.esiea.auroraskyesapi.aeroport.service;

import com.esiea.auroraskyesapi.aeroport.dto.AeroportGlobaleDTO;
import com.esiea.auroraskyesapi.aeroport.mapper.AeroportMapper;
import com.esiea.auroraskyesapi.vol.dto.VolGlobalDTO;
import com.esiea.auroraskyesback.exception.ExternalApiException;
import com.esiea.auroraskyesdbaccess.aeroport.dto.AeroportBDDTO;
import com.esiea.auroraskyesdbaccess.vol.dto.VolBDDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
public class AeroportServiceImpl implements AeroportService {

	private static final String API_BASE_URL = "http://localhost:8081";
	private static final String API_KEY = "aled_aled_aled_aled_aled";

	private final RestTemplate restTemplate;

	private final AeroportMapper aeroportMapper;

	public AeroportServiceImpl(RestTemplate restTemplate,
							   AeroportMapper aeroportMapper) {
		this.restTemplate = restTemplate;
		this.aeroportMapper = aeroportMapper;
	}

	/**
	 * Appelle l'API externe pour récupérer la liste des aéroports.
	 *
	 * @return liste des AeroportBDDTO
	 */
	@Override
	public List<AeroportBDDTO> getAllAeroports() {
		String fullUrl = buildUrl(API_BASE_URL + "/aeroports");

		try {
			ResponseEntity<AeroportBDDTO[]> response = makeRequest(fullUrl, HttpMethod.GET, null, AeroportBDDTO[].class);
			return Arrays.asList(response.getBody());
		} catch (Exception e) {
			throw new ExternalApiException("Erreur lors de l'appel à l'API externe", e);
		}
	}

	/** {@inheritDoc} */
	@Override
	public AeroportBDDTO createAeroport(AeroportGlobaleDTO aeroportGlobaleDTO) {
		return postVol(this.aeroportMapper.aeroportGlobaleDTOToAeroportBDDTO(aeroportGlobaleDTO));
	}

	private AeroportBDDTO postVol(AeroportBDDTO vol) {
		String fullUrl = buildUrl(API_BASE_URL + "/aeroports/external");
		try {
			return makeRequest(fullUrl, HttpMethod.POST, vol, AeroportBDDTO.class).getBody();
		} catch (Exception e) {
			throw new com.esiea.auroraskyesapi.exception.ExternalApiException("Erreur lors de la création de la réservation.", e);
		}
	}

	/**
	 * Construit une URL complète à partir d'une base.
	 *
	 * @param baseUrl L'URL de base.
	 * @return L'URL complète.
	 */
	private String buildUrl(String baseUrl) {
		return UriComponentsBuilder.fromUriString(baseUrl)
				.build()
				.toUriString();
	}

	/**
	 * Effectue une requête HTTP avec des paramètres génériques.
	 *
	 * @param url          L'URL cible.
	 * @param method       La méthode HTTP (GET, POST, etc.).
	 * @param requestBody  Le corps de la requête (peut être null).
	 * @param responseType Le type de la réponse attendue.
	 * @param <T>          Le type de la réponse.
	 * @return La réponse sous forme d'un objet {@link ResponseEntity}.
	 */
	private <T> ResponseEntity<T> makeRequest(String url, HttpMethod method, Object requestBody, Class<T> responseType) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("x-api-key", API_KEY);

		HttpEntity<Object> requestEntity = new HttpEntity<>(requestBody, headers);
		return restTemplate.exchange(url, method, requestEntity, responseType);
	}
}
