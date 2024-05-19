package com.example.restservice.constant;

import lombok.Getter;

@Getter
public enum LoanType {

    CONSUMER("consumer"),
    STUDENT("consumer");

    private final String label;

    LoanType(String label){
        this.label = label;
    }

}
