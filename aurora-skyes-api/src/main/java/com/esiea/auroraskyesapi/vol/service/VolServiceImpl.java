package com.esiea.auroraskyesapi.vol.service;

import com.esiea.auroraskyesapi.exception.ExternalApiException;
import com.esiea.auroraskyesapi.vol.dto.VolGlobalDTO;
import com.esiea.auroraskyesapi.vol.mapper.VolMapper;
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
public class VolServiceImpl implements VolService {

	private static final String API_BASE_URL = "http://localhost:8081/vols";
	private static final String API_KEY = "aled_aled_aled_aled_aled";

	private final VolMapper volMapper;

	private final RestTemplate restTemplate;

	public VolServiceImpl(RestTemplate restTemplate,
						  VolMapper volMapper) {
		this.restTemplate = restTemplate;
		this.volMapper = volMapper;
	}

	/** {@inheritDoc} */
	@Override
	public List<VolBDDTO> getAllVols() {
		String fullUrl = buildUrl(API_BASE_URL);
		try {
			ResponseEntity<VolBDDTO[]> response = makeRequest(fullUrl, HttpMethod.GET, null, VolBDDTO[].class);
			return Arrays.asList(response.getBody());
		} catch (Exception e) {
			throw new ExternalApiException("Erreur lors de l'appel à l'API externe pour récupérer tous les vols.", e);
		}
	}

	/** {@inheritDoc} */
	@Override
	public VolBDDTO findVolById(Long id) {
		String fullUrl = buildUrl(API_BASE_URL + "/" + id);
		try {
			return makeRequest(fullUrl, HttpMethod.GET, null, VolBDDTO.class).getBody();
		} catch (Exception e) {
			throw new ExternalApiException("Erreur lors de l'appel à l'API externe pour le vol avec ID " + id, e);
		}
	}

	/** {@inheritDoc} */
	@Override
	public VolBDDTO createVol(VolGlobalDTO volGlobalDTO) {
		return postVol(this.volMapper.volGlobalDTOToVolBDDTO(volGlobalDTO));
	}

	private VolBDDTO postVol(VolBDDTO vol) {
		String fullUrl = buildUrl(API_BASE_URL + "/reservations/external");
		try {
			return makeRequest(fullUrl, HttpMethod.POST, vol, VolBDDTO.class).getBody();
		} catch (Exception e) {
			throw new ExternalApiException("Erreur lors de la création de la réservation.", e);
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
	 * Crée des en-têtes HTTP avec la clé API.
	 *
	 * @return Les en-têtes HTTP.
	 */
	private HttpHeaders createHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("x-api-key", API_KEY);
		return headers;
	}

	/**
	 * Effectue une requête HTTP avec des paramètres génériques.
	 *
	 * @param url         L'URL cible.
	 * @param method      La méthode HTTP (GET, POST, etc.).
	 * @param requestBody Le corps de la requête (peut être null).
	 * @param responseType Le type de la réponse attendue.
	 * @param <T>         Le type de la réponse.
	 * @return La réponse sous forme d'un objet {@link ResponseEntity}.
	 */
	private <T> ResponseEntity<T> makeRequest(String url, HttpMethod method, Object requestBody, Class<T> responseType) {
		HttpEntity<Object> requestEntity = new HttpEntity<>(requestBody, createHeaders());
		return restTemplate.exchange(url, method, requestEntity, responseType);
	}

}
