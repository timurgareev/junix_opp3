package com.gta.spring.springboot.junix_opp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EntityAlreadyExistsException extends ResponseStatusException {

    public EntityAlreadyExistsException(String reason) {
        super(HttpStatus.CONFLICT, reason);
    }
}
