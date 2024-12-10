package com.esiea.auroraskyesback.exception.controller;

import com.esiea.auroraskyesback.authentification.exception.InvalidPasswordException;
import com.esiea.auroraskyesback.monnaie.exception.MonnaieNotFoundException;
import com.esiea.auroraskyesback.monnaie.exception.MonnaieUpdateException;
import com.esiea.auroraskyesback.reservation.exception.NoAvailableSeatsException;
import com.esiea.auroraskyesback.reservation.exception.ReservationNotFoundException;
import com.esiea.auroraskyesback.utilisateur.exception.InvalidUtilisateurException;
import com.esiea.auroraskyesback.utilisateur.exception.UtilisateurNotFoundException;
import com.esiea.auroraskyesback.vol.exception.InvalidVolException;
import com.esiea.auroraskyesback.vol.exception.VolNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VolNotFoundException.class)
    public ResponseEntity<String> handleVolNotFoundException(VolNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidVolException.class)
    public ResponseEntity<String> handleInvalidVolException(InvalidVolException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UtilisateurNotFoundException.class)
    public ResponseEntity<String> handleUtilisateurNotFoundException(UtilisateurNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidUtilisateurException.class)
    public ResponseEntity<String> handleInvalidUtilisateurException(InvalidUtilisateurException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<String> handleInvalidPasswordException(InvalidPasswordException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(MonnaieNotFoundException.class)
    public ResponseEntity<String> handleMonnaieNotFoundException(MonnaieNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(MonnaieUpdateException.class)
    public ResponseEntity<String> handleMonnaieUpdateException(MonnaieUpdateException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<String> handleReservationNotFoundException(ReservationNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(NoAvailableSeatsException.class)
    public ResponseEntity<String> handleNoAvailableSeatsException(NoAvailableSeatsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    /**
     * Gére toutes les autres exceptions
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>("Une erreur est survenue: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}