package com.esiea.auroraskyesback.error.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class ErrorService {

    private final ConcurrentLinkedQueue<String> errorQueue = new ConcurrentLinkedQueue<>();
    @Getter
    private final Sinks.Many<String> errorSink = Sinks.many().multicast().onBackpressureBuffer();

    public void logError(String errorMessage) {
        errorQueue.add(errorMessage);
        errorSink.tryEmitNext(errorMessage);
    }

}

