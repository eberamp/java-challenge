package com.example.restservice.model;

// To avoid the boilerplate of getters and setter I leveraged the use of Lombok @Data
// If the project was set in Java 17 we could use records or Data classes in Kotlin instead

import com.example.restservice.constant.LoanType;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Loan {

	@NotNull private Long loanId;
	private Double requestedAmount;
	private Integer termMonths;
	private Double annualInterest;
	@NotNull private LoanType type;
	@NotNull private Borrower borrower;

	public Double getMonthlyInterestRate(){
		return (annualInterest / 12) / 100;
	}

}