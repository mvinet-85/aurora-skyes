package com.esiea.auroraskyesback.exception.controller;

import com.esiea.auroraskyesback.vol.exception.InvalidVolException;
import com.esiea.auroraskyesback.vol.exception.VolNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(VolNotFoundException.class)
    public ResponseEntity<String> handleVolNotFoundException(VolNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidVolException.class)
    public ResponseEntity<String> handleInvalidVolException(InvalidVolException ex) {
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