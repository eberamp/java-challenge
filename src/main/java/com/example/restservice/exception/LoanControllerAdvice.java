package com.example.restservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class LoanControllerAdvice {

    @ExceptionHandler(UnsupportedLoanTypeException.class)
    public ResponseEntity<ErrorResponse> handleUnsupportedLoanType(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse());
    }
}
