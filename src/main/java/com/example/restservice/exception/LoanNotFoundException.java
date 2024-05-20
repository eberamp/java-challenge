package com.example.restservice.exception;

public class LoanNotFoundException extends RuntimeException {

    public LoanNotFoundException(Long loanId){
        super("Loan: " + loanId);
    }

}
