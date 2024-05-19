package com.example.restservice.model;

// To avoid the boilerplate of getters and setter I leveraged the use of Lombok @Data
// If the project was set in Java 17 we could use records or Data classes in Kotlin instead

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
public class Borrower {

	@NotBlank
	@Size(max = 100)
	private String name;

	@Positive
	@Range(min = 18, max = 100)
	private Integer age;

	@NotNull
	private Double annualIncome;
	private Boolean delinquentDebt;

	@NotNull
	private Double annualDebt;

	@Min(0)
	private Integer creditHistory;

}