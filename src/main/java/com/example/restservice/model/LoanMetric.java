package com.example.restservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

// To avoid the boilerplate of getters and setter I leveraged the use of Lombok @Data
// If the project was set in Java 17 we could use records or Data classes in Kotlin instead

@Data
@AllArgsConstructor
public class LoanMetric {

	private Double monthlyInterestRate;
	private Double monthlyPayment;

}