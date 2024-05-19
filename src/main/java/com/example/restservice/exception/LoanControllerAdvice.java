package com.example.restservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LoanControllerAdvice {

    @ExceptionHandler(UnsupportedLoanTypeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleUnsupportedLoanType(UnsupportedLoanTypeException e){
        return new ErrorResponse("The loan type is not supported");
    }

    @ExceptionHandler(LoanNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleUnsupportedLoanType(LoanNotFoundException e){
        return new ErrorResponse("The requested loan was not found " + e.getMessage());
    }

    // We could create a more specialized Exception that is thrown when we validate the ids requested
    // for simplicity I'll just use NumberFormatException and send a generic message
    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleNumberFormatException(NumberFormatException e){
        return new ErrorResponse("Invalid input format");
    }
}
