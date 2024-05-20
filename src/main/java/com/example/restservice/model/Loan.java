package com.example.restservice.model;

// To avoid the boilerplate of getters and setter I leveraged the use of Lombok @Data
// If the project was set in Java 17 we could use records or Data classes in Kotlin instead

import com.example.restservice.constant.LoanType;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class Loan {

	@NotNull
	private Long loanId;

	@NotNull
	@Positive
	// Maybe there's a business rule to validate that the requested amount must be a certain amount
	@Min(value = 1, message = "the requested amount must be valid")
	private Double requestedAmount;

	@NotNull
	@Positive
	@Min(value = 1, message = "term months need to be equal or greater than 1")
	private Integer termMonths;

	@NotNull
	@Positive
	private Double annualInterest;

	@NotNull
	private LoanType type;

	@NotNull
	private Long loanOfficerId; // This didn't exist originally, but I see it in the sample request so I added it

	@NotNull
	@Valid
	private Borrower borrower;

	public Double getMonthlyInterestRate(){
		return (annualInterest / 12) / 100;
	}

}