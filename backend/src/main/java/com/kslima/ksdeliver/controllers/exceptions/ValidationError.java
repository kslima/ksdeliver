package com.kslima.ksdeliver.controllers.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ValidationError extends RequestError{

    private final Map<String, String> validationErrors = new HashMap<>();

    public ValidationError() {

    }

    public Map<String, String> getValidationErrors() {
        return validationErrors;
    }
}
