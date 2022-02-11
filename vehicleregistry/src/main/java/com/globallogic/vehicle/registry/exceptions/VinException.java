package com.globallogic.vehicle.registry.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VinException extends RegistryException {
    public VinException(String message) {
        super(message);
    }
}
