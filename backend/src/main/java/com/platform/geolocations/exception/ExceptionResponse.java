package com.platform.geolocations.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResponse {

    private String errorCode;
    private String message;
    private Map<String, String> errors;

    private LocalDateTime timestamp = LocalDateTime.now();

    public ExceptionResponse(String errorCode, Map<String, String> errors) {
        this.errorCode = errorCode;
        this.errors = errors;
    }

    public ExceptionResponse(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public static ExceptionResponse from(ConstraintViolationException ex) {
        String errorCode = ExceptionCodes.VALIDATION_ERROR;
        Map<String, String> errors = buildErrors(ex);

        return new ExceptionResponse(errorCode, errors);
    }

    private static Map<String, String> buildErrors(ConstraintViolationException ex) {

        return ex.getConstraintViolations()
                .stream()
                .collect(Collectors.toMap(
                        ExceptionResponse::getPropertyName, ConstraintViolation::getMessage)
                );
    }

    private static String getPropertyName(ConstraintViolation<?> violation) {
        String path = violation.getPropertyPath().toString();
        if (path != null) {
            int lastDotIndex = path.lastIndexOf('.');
            return path.substring(lastDotIndex + 1);
        }

        return null;
    }
}