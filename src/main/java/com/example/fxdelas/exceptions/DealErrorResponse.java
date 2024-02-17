package com.example.fxdelas.exceptions;

public class DealErrorResponse {
    private int status;
    private String message;

    public DealErrorResponse() {
    }

    public DealErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
