package com.example.restservice.model;

// To avoid the boilerplate of getters and setter I leveraged the use of Lombok @Data
// If the project was set in Java 17 we could use records or Data classes in Kotlin instead

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Borrower {

	private String name;
	private Integer age;
	private Double annualIncome;
	private Boolean delinquentDebt;
	private Double annualDebt;
	private Integer creditHistory;

}