package com.example.restservice.exception;

public class UnsupportedLoanTypeException extends RuntimeException {

    public UnsupportedLoanTypeException(String message){
        super(message);
    }
}
