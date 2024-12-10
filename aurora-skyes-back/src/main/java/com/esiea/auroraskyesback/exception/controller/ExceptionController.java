package com.esiea.auroraskyesback.exception.controller;

import com.esiea.auroraskyesback.monnaie.dto.MonnaieDTO;
import com.esiea.auroraskyesback.monnaie.service.MonnaieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@ControllerAdvice
public class ExceptionController {

    /**
     * Gére une exception spécifique
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        return new ResponseEntity<>("Erreur: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
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