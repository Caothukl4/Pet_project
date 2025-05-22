package com.example.petproject.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(APIException.class)
    public ResponseEntity<ErrorResponse> handleAPIException(APIException ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getHttpCode().value(),
                ex.getMessage()
        );
        return ResponseEntity.status(ex.getHttpCode()).body(error);
    }

    // Các handler khác nếu cần
}

