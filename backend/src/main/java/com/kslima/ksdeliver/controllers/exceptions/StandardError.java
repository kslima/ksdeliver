package com.kslima.ksdeliver.controllers.exceptions;


public class StandardError extends RequestError {


    private String error;


    public StandardError() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
