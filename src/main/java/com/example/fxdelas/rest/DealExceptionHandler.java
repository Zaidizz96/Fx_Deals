package com.example.fxdelas.rest;

import com.example.fxdelas.exceptions.DealErrorResponse;
import com.example.fxdelas.exceptions.DealNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DealExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<DealErrorResponse> handler (DealNotFoundException dealNotFoundException){
        DealErrorResponse dealErrorResponse = new DealErrorResponse();
        dealErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        dealErrorResponse.setMessage(dealNotFoundException.getMessage());

        return new ResponseEntity<>(dealErrorResponse , HttpStatus.NOT_FOUND);

    }
}
