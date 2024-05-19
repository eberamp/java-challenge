package com.example.restservice.constant;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public enum LoanType {

    @JsonProperty("consumer")
    CONSUMER("consumer"),

    @JsonProperty("student")
    STUDENT("student");

    private final String label;

    LoanType(String label){
        this.label = label;
    }

}
