package com.esiea.auroraskyesback.utilisateur.service;

import com.esiea.auroraskyesback.authentification.service.AuthentificationService;
import com.esiea.auroraskyesback.exception.ExternalApiException;
import com.esiea.auroraskyesback.utilisateur.dto.UtilisateurDTO;
import com.esiea.auroraskyesback.utilisateur.exception.InvalidUtilisateurException;
import com.esiea.auroraskyesback.utilisateur.mapper.UtilisateurMapper;
import com.esiea.auroraskyesdbaccess.utilisateur.dto.UtilisateurBDDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;

@Service
public class UtilisateurServiceImpl implements UserDetailsService, UtilisateurService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UtilisateurServiceImpl.class);

    private static final String API_BASE_URL = "http://localhost:8081";
    private static final String API_KEY = "aled_aled_aled_aled_aled";

    private final RestTemplate restTemplate;
    private final AuthentificationService authentificationService;
    private final UtilisateurMapper utilisateurMapper;

    public UtilisateurServiceImpl(RestTemplate restTemplate,
                                  AuthentificationService authentificationService,
                                  UtilisateurMapper utilisateurMapper) {
        this.restTemplate = restTemplate;
        this.authentificationService = authentificationService;
        this.utilisateurMapper = utilisateurMapper;
    }

    /** {@inheritDoc} */
    @Transactional
    @Override
    public UtilisateurBDDTO creerUtilisateur(UtilisateurDTO utilisateurDTO) {
        validateUtilisateurDTO(utilisateurDTO);

        String fullUrl = buildUrl(API_BASE_URL + "/utilisateurs");

        try {
            // Hacher le mot de passe
            utilisateurDTO.setMotDePasse(authentificationService.hashMotDePasse(utilisateurDTO.getMotDePasse()));
            UtilisateurBDDTO utilisateurBDDTO = this.utilisateurMapper.utilisateurDTOToUtilisateurBDDTO(utilisateurDTO);

            // Effectuer la requête POST
            return makeRequest(fullUrl, HttpMethod.POST, utilisateurBDDTO , UtilisateurBDDTO.class).getBody();
        } catch (Exception e) {
            LOGGER.error("Erreur lors de la création d'un utilisateur : {}", e.getMessage(), e);
            throw new ExternalApiException("Erreur lors de l'appel à l'API externe pour créer un utilisateur.", e);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        String fullUrl = buildUrl(API_BASE_URL + "/utilisateurs/email/" + email);

        try {
            UtilisateurBDDTO utilisateur = makeRequest(fullUrl, HttpMethod.GET, null, UtilisateurBDDTO.class).getBody();
            return new User(utilisateur.getEmail(), utilisateur.getMotDePasse(), new ArrayList<>());
        } catch (Exception e) {
            LOGGER.error("Erreur lors de la récupération de l'utilisateur avec email {} : {}", email, e.getMessage(), e);
            throw new ExternalApiException("Erreur lors de l'appel à l'API externe pour l'utilisateur : " + email, e);
        }
    }

    /**
     * Valide les champs obligatoires d'un utilisateur DTO.
     *
     * @param utilisateurDTO L'utilisateur à valider.
     */
    private void validateUtilisateurDTO(UtilisateurDTO utilisateurDTO) {
        if (utilisateurDTO.getEmail() == null || utilisateurDTO.getMotDePasse() == null) {
            LOGGER.error("Les champs email et mot de passe sont obligatoires");
            throw new InvalidUtilisateurException("Les champs email et mot de passe sont obligatoires");
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
     * @param url          L'URL cible.
     * @param method       La méthode HTTP (GET, POST, etc.).
     * @param requestBody  Le corps de la requête (peut être null).
     * @param responseType Le type de la réponse attendue.
     * @param <T>          Le type de la réponse.
     * @return La réponse sous forme d'un objet {@link ResponseEntity}.
     */
    private <T> ResponseEntity<T> makeRequest(String url, HttpMethod method, Object requestBody, Class<T> responseType) {
        try {
            // Créer l'entité de requête avec les headers
            HttpHeaders headers = createHeaders();
            HttpEntity<Object> requestEntity = new HttpEntity<>(requestBody, headers);

            // Log de la requête
            LOGGER.info("Envoi de la requête HTTP");
            LOGGER.info("URL: {}", url);
            LOGGER.info("Méthode: {}", method);
            LOGGER.info("Headers: {}", headers);
            if (requestBody != null) {
                LOGGER.info("Corps de la requête: {}", requestBody);
            } else {
                LOGGER.info("Aucun corps de requête.");
            }

            // Effectuer la requête
            ResponseEntity<T> response = restTemplate.exchange(url, method, requestEntity, responseType);

            // Log de la réponse
            LOGGER.info("Réponse reçue avec statut: {}", response.getStatusCode());
            if (response.getBody() != null) {
                LOGGER.info("Corps de la réponse: {}", response.getBody());
            } else {
                LOGGER.info("La réponse ne contient aucun corps.");
            }

            return response;
        } catch (Exception e) {
            // Log de l'erreur
            LOGGER.error("Erreur lors de la requête HTTP: {}", e.getMessage(), e);
            throw new ExternalApiException("Erreur lors de l'appel à l'API externe.", e);
        }
    }

}
