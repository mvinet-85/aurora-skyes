package com.esiea.auroraskyesapi.reservation.service;

import com.esiea.auroraskyesapi.reservation.dto.ReservationGlobaleDTO;
import com.esiea.auroraskyesapi.reservation.mapper.ReservationMapper;
import com.esiea.auroraskyesback.exception.ExternalApiException;
import com.esiea.auroraskyesdbaccess.reservation.dto.ReservationBDDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ReservationServiceImpl implements ReservationService {

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
    @Override
    public ReservationBDDTO createReservation(ReservationGlobaleDTO reservationGlobaleDTO) {
        return postReservation(this.reservationMapper.reservationGlobaleDTOToReservationBDDTO(reservationGlobaleDTO));
    }

    @Override
    public ReservationBDDTO getReservation(Long id) {
        return fetchReservationById(id);
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
        String fullUrl = buildUrl(API_BASE_URL + "/reservations/external");
        try {
            return makeRequest(fullUrl, HttpMethod.POST, reservation, ReservationBDDTO.class).getBody();
        } catch (Exception e) {
            throw new ExternalApiException("Erreur lors de la création de la réservation.", e);
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
