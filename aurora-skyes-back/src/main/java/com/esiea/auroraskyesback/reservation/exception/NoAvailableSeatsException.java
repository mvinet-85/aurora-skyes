package com.esiea.auroraskyesback.reservation.exception;

import lombok.Getter;

@Getter
public class NoAvailableSeatsException extends RuntimeException {

    private Long volId;

    public NoAvailableSeatsException(String message, Long volId) {
        super(message);
        this.volId = volId;
    }

}
