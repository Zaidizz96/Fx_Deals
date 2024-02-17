package com.example.fxdelas.exceptions;

public class DealNotFoundException extends RuntimeException{


    public DealNotFoundException(String message) {
        super(message);
    }

    public DealNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DealNotFoundException(Throwable cause) {
        super(cause);
    }

}
