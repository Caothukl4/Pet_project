package com.example.petproject.exception;

import org.springframework.http.HttpStatus;

public class APIException extends RuntimeException {
    private HttpStatus httpCode;
    public APIException(String message,int httpCode) {
        super(message);
        this.httpCode = HttpStatus.valueOf(httpCode);
    }

    public HttpStatus getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(HttpStatus httpCode) {
        this.httpCode = httpCode;
    }
}
