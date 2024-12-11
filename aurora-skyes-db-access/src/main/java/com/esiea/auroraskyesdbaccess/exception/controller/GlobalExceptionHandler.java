package com.esiea.auroraskyesdbaccess.exception.controller;

import com.esiea.auroraskyesdbaccess.authentification.exception.InvalidPasswordException;
import com.esiea.auroraskyesdbaccess.monnaie.exception.MonnaieNotFoundException;
import com.esiea.auroraskyesdbaccess.monnaie.exception.MonnaieUpdateException;
import com.esiea.auroraskyesdbaccess.reservation.exception.NoAvailableSeatsException;
import com.esiea.auroraskyesdbaccess.reservation.exception.ReservationNotFoundException;
import com.esiea.auroraskyesdbaccess.utilisateur.exception.InvalidUtilisateurException;
import com.esiea.auroraskyesdbaccess.utilisateur.exception.UtilisateurNotFoundException;
import com.esiea.auroraskyesdbaccess.vol.exception.InvalidVolException;
import com.esiea.auroraskyesdbaccess.vol.exception.VolNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Gère l'exception VolNotFoundException.
     *
     * @param ex l'exception levée lorsque le vol n'est pas trouvé.
     * @return la réponse HTTP avec un message d'erreur et le code de statut 404 (NOT_FOUND).
     */
    @ExceptionHandler(VolNotFoundException.class)
    public ResponseEntity<String> handleVolNotFoundException(VolNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Gère l'exception InvalidVolException.
     *
     * @param ex l'exception levée lorsqu'un vol est invalide.
     * @return la réponse HTTP avec un message d'erreur et le code de statut 400 (BAD_REQUEST).
     */
    @ExceptionHandler(InvalidVolException.class)
    public ResponseEntity<String> handleInvalidVolException(InvalidVolException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    /**
     * Gère l'exception UtilisateurNotFoundException.
     *
     * @param ex l'exception levée lorsqu'un utilisateur n'est pas trouvé.
     * @return la réponse HTTP avec un message d'erreur et le code de statut 404 (NOT_FOUND).
     */
    @ExceptionHandler(UtilisateurNotFoundException.class)
    public ResponseEntity<String> handleUtilisateurNotFoundException(UtilisateurNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Gère l'exception InvalidUtilisateurException.
     *
     * @param ex l'exception levée lorsqu'un utilisateur est invalide.
     * @return la réponse HTTP avec un message d'erreur et le code de statut 400 (BAD_REQUEST).
     */
    @ExceptionHandler(InvalidUtilisateurException.class)
    public ResponseEntity<String> handleInvalidUtilisateurException(InvalidUtilisateurException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    /**
     * Gère l'exception InvalidPasswordException.
     *
     * @param ex l'exception levée lorsqu'un mot de passe est invalide.
     * @return la réponse HTTP avec un message d'erreur et le code de statut 400 (BAD_REQUEST).
     */
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<String> handleInvalidPasswordException(InvalidPasswordException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    /**
     * Gère l'exception MonnaieNotFoundException.
     *
     * @param ex l'exception levée lorsqu'une monnaie n'est pas trouvée.
     * @return la réponse HTTP avec un message d'erreur et le code de statut 404 (NOT_FOUND).
     */
    @ExceptionHandler(MonnaieNotFoundException.class)
    public ResponseEntity<String> handleMonnaieNotFoundException(MonnaieNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Gère l'exception MonnaieUpdateException.
     *
     * @param ex l'exception levée lorsqu'une mise à jour de monnaie échoue.
     * @return la réponse HTTP avec un message d'erreur et le code de statut 500 (INTERNAL_SERVER_ERROR).
     */
    @ExceptionHandler(MonnaieUpdateException.class)
    public ResponseEntity<String> handleMonnaieUpdateException(MonnaieUpdateException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    /**
     * Gère l'exception ReservationNotFoundException.
     *
     * @param ex l'exception levée lorsqu'une réservation n'est pas trouvée.
     * @return la réponse HTTP avec un message d'erreur et le code de statut 404 (NOT_FOUND).
     */
    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<String> handleReservationNotFoundException(ReservationNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Gère l'exception NoAvailableSeatsException.
     *
     * @param ex l'exception levée lorsqu'il n'y a pas de places disponibles pour une réservation.
     * @return la réponse HTTP avec un message d'erreur et le code de statut 400 (BAD_REQUEST).
     */
    @ExceptionHandler(NoAvailableSeatsException.class)
    public ResponseEntity<String> handleNoAvailableSeatsException(NoAvailableSeatsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    /**
     * Gère toutes les autres exceptions non traitées spécifiquement par les autres gestionnaires d'exceptions.
     *
     * @param ex l'exception générale levée.
     * @param request l'objet WebRequest contenant des informations supplémentaires sur la demande.
     * @return la réponse HTTP avec un message générique et le code de statut 500 (INTERNAL_SERVER_ERROR).
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>("Une erreur est survenue: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}