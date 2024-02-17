package com.example.fxdelas.exceptions;

import org.springframework.http.HttpStatus;

public class DealValidationException extends RuntimeException{

    public DealValidationException(String message) {
        super(message);
    }

    public DealValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DealValidationException(Throwable cause) {
        super(cause);
    }

}
