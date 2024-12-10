package com.esiea.auroraskyesback.reservation.exception;

public class NoAvailableSeatsException extends RuntimeException {
    public NoAvailableSeatsException(String message) {
        super(message);
    }
}
