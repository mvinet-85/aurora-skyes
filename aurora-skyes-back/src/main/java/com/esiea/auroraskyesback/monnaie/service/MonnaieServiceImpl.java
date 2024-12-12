package com.esiea.auroraskyesback.monnaie.service;

import com.esiea.auroraskyesback.exception.ExternalApiException;
import com.esiea.auroraskyesback.monnaie.exception.MonnaieNotFoundException;
import com.esiea.auroraskyesdbaccess.monnaie.dto.MonnaieBDDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class MonnaieServiceImpl implements MonnaieService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonnaieServiceImpl.class);

    private static final String API_BASE_URL = "http://localhost:8081";
    private static final String API_KEY = "aled_aled_aled_aled_aled";

    private final RestTemplate restTemplate;

    public MonnaieServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /** {@inheritDoc} */
    @Override
    public List<MonnaieBDDTO> getAllMonnaies() {
        String fullUrl = buildUrl(API_BASE_URL + "/monnaies");

        try {
            ResponseEntity<MonnaieBDDTO[]> response = makeRequest(fullUrl, HttpMethod.GET, null, MonnaieBDDTO[].class);
            MonnaieBDDTO[] monnaies = response.getBody();

            if (monnaies.length == 0) {
                LOGGER.warn("Aucune monnaie trouvée.");
                throw new MonnaieNotFoundException("Aucune monnaie disponible dans la base de données.");
            }

            return Arrays.asList(monnaies);
        } catch (MonnaieNotFoundException e) {
            throw e; // Propager l'exception métier
        } catch (Exception e) {
            LOGGER.error("Erreur lors de l'appel à l'API externe : {}", e.getMessage(), e);
            throw new ExternalApiException("Erreur lors de l'appel à l'API externe", e);
        }
    }

    // ----------- Méthodes utilitaires -----------

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
