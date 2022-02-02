package com.globallogic.vehicle.registry.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoSuchPartException extends RegistryException {

    public NoSuchPartException(String message) {
        super(message);
    }

    public NoSuchPartException(Throwable e, String message) {
        super(e, message);
    }
}
