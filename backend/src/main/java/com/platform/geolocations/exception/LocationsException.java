package com.platform.geolocations.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LocationsException extends ApiException {

    public  LocationsException(String message) {
        super(message);
    }
}
