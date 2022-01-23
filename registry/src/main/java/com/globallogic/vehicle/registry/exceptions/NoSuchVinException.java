package com.globallogic.vehicle.registry.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchVinException extends RegistryException {
    public NoSuchVinException(String message) {
        super(message);
    }

    public NoSuchVinException(Throwable e, String message) {
        super(e, message);
    }
}
