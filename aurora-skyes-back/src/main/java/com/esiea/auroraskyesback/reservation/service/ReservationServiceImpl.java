package com.esiea.auroraskyesback.reservation.service;

import com.esiea.auroraskyesback.exception.ExternalApiException;
import com.esiea.auroraskyesback.reservation.dto.ReservationDTO;
import com.esiea.auroraskyesback.reservation.exception.NoAvailableSeatsException;
import com.esiea.auroraskyesback.reservation.mapper.ReservationMapper;
import com.esiea.auroraskyesdbaccess.reservation.dto.ReservationBDDTO;
import com.esiea.auroraskyesdbaccess.reservation.model.Classe;
import com.esiea.auroraskyesdbaccess.utilisateur.dto.UtilisateurBDDTO;
import com.esiea.auroraskyesdbaccess.vol.dto.VolBDDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

    private static final String API_BASE_URL = "http://localhost:8081";
    private static final String API_KEY = "aled_aled_aled_aled_aled";

    private final RestTemplate restTemplate;
    private final ReservationMapper reservationMapper;

    public ReservationServiceImpl(RestTemplate restTemplate,
                                  ReservationMapper reservationMapper) {
        this.restTemplate = restTemplate;
        this.reservationMapper = reservationMapper;
    }

    /** {@inheritDoc} */
    @Transactional
    @Override
    public ReservationBDDTO createReservation(ReservationDTO reservationDTO) {
        UtilisateurBDDTO utilisateur = fetchUtilisateurById(reservationDTO.getUserId());
        VolBDDTO vol = fetchVolById(reservationDTO.getVolId());

        if (vol.getPlaceDisponible() <= 0) {
            LOGGER.error("Aucune place disponible pour le vol ID : {}", vol.getId());
            throw new NoAvailableSeatsException("Aucune place disponible pour le vol ID : " + vol.getId());
        }

        // Mise à jour des places disponibles
        vol.setPlaceDisponible(vol.getPlaceDisponible() - 1);
        updateVol(vol);

        // Préparer la réservation
        ReservationBDDTO reservation = reservationMapper.reservationDTOToReservationBDDTO(reservationDTO);
        reservation.setUser(utilisateur);
        reservation.setVol(vol);

        // Calculer le prix
        double prix = vol.getPrix();
        if (Classe.FIRST.equals(reservationDTO.getClasse())) {
            prix *= 1.2;
        }
        if (vol.isEscale()) {
            prix *= 0.8;
        }
        reservation.setPrix(prix);

        return postReservation(reservation);
    }

    @Transactional
    @Override
    public ReservationBDDTO updateReservation(ReservationDTO reservationDTO) {
        ReservationBDDTO existingReservation = fetchReservationById(reservationDTO.getId());
        existingReservation.setSiege(reservationDTO.getSiege());
        existingReservation.setClasse(reservationDTO.getClasse());

        updateReservation(existingReservation);

        return fetchReservationById(reservationDTO.getId());
    }

    @Override
    public ReservationBDDTO getReservation(Long id) {
        return fetchReservationById(id);
    }

    @Override
    public List<ReservationBDDTO> getUserReservation(Long id) {
        String fullUrl = buildUrl(API_BASE_URL + "/reservations/user/" + id);
        try {
            return Arrays.asList(makeRequest(fullUrl, HttpMethod.GET, null, ReservationBDDTO[].class).getBody());
        } catch (Exception e) {
            throw new ExternalApiException("Erreur lors de l'appel à l'API externe pour les réservations utilisateur.", e);
        }
    }

    private UtilisateurBDDTO fetchUtilisateurById(Long userId) {
        String fullUrl = buildUrl(API_BASE_URL + "/utilisateurs/" + userId);
        try {
            return makeRequest(fullUrl, HttpMethod.GET, null, UtilisateurBDDTO.class).getBody();
        } catch (Exception e) {
            throw new ExternalApiException("Erreur lors de l'appel à l'API externe pour récupérer l'utilisateur.", e);
        }
    }

    private VolBDDTO fetchVolById(Long volId) {
        String fullUrl = buildUrl(API_BASE_URL + "/vols/" + volId);
        try {
            return makeRequest(fullUrl, HttpMethod.GET, null, VolBDDTO.class).getBody();
        } catch (Exception e) {
            throw new ExternalApiException("Erreur lors de l'appel à l'API externe pour récupérer le vol.", e);
        }
    }

    private void updateVol(VolBDDTO vol) {
        String fullUrl = buildUrl(API_BASE_URL + "/vols");
        try {
            makeRequest(fullUrl, HttpMethod.PUT, vol, Void.class);
        } catch (Exception e) {
            throw new ExternalApiException("Erreur lors de la mise à jour des informations du vol.", e);
        }
    }

    private ReservationBDDTO fetchReservationById(Long reservationId) {
        String fullUrl = buildUrl(API_BASE_URL + "/reservations/" + reservationId);
        try {
            return makeRequest(fullUrl, HttpMethod.GET, null, ReservationBDDTO.class).getBody();
        } catch (Exception e) {
            throw new ExternalApiException("Erreur lors de l'appel à l'API externe pour récupérer la réservation.", e);
        }
    }

    private ReservationBDDTO postReservation(ReservationBDDTO reservation) {
        String fullUrl = buildUrl(API_BASE_URL + "/reservations");
        try {
            return makeRequest(fullUrl, HttpMethod.POST, reservation, ReservationBDDTO.class).getBody();
        } catch (Exception e) {
            throw new ExternalApiException("Erreur lors de la création de la réservation.", e);
        }
    }

    private void updateReservation(ReservationBDDTO reservation) {
        String fullUrl = buildUrl(API_BASE_URL + "/reservations/" + reservation.getId());
        try {
            makeRequest(fullUrl, HttpMethod.PUT, reservation, Void.class);
        } catch (Exception e) {
            throw new ExternalApiException("Erreur lors de la mise à jour de la réservation.", e);
        }
    }

    private String buildUrl(String baseUrl) {
        return UriComponentsBuilder.fromUriString(baseUrl)
                .build()
                .toUriString();
    }

    private <T> ResponseEntity<T> makeRequest(String url, HttpMethod method, Object requestBody, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", API_KEY);
        HttpEntity<Object> requestEntity = new HttpEntity<>(requestBody, headers);
        return restTemplate.exchange(url, method, requestEntity, responseType);
    }
}
