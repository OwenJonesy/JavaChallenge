package com.challenge.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * @implNote The purpose of this is to provide a more detailed Exception Message
 * with a JSON style response message for more clear and customised reasoning
 */
@ControllerAdvice //Signals that this advice is rendered straight into the response body.
public class RestExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatusException(ResponseStatusException ex) {
        Map<String, Object> body = new HashMap<>();

        body.put("timestamp", Instant.now());
        body.put("status", ex.getStatusCode().value());          // use getStatusCode() now
        body.put("error", ex.getStatusCode().toString());       // or ex.getStatusCode().name()
        body.put("message", ex.getReason());

        return new ResponseEntity<>(body, ex.getStatusCode());
    }
}
