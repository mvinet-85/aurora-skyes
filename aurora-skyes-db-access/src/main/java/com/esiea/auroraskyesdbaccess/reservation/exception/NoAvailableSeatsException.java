package com.esiea.auroraskyesdbaccess.reservation.exception;

public class NoAvailableSeatsException extends RuntimeException {
    public NoAvailableSeatsException(String message) {
        super(message);
    }
}
