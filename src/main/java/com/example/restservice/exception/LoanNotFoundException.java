package com.example.restservice.exception;

public class LoanNotFoundException extends RuntimeException {

    public LoanNotFoundException(String message){
        super(message);
    }

}
