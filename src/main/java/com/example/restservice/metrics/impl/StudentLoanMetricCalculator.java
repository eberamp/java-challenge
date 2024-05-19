package com.example.restservice.metrics.impl;

import org.springframework.stereotype.Component;

import com.example.restservice.metrics.ILoanMetricCalculator;
import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;

@Component
public class StudentLoanMetricCalculator implements ILoanMetricCalculator {

	public static final double SPECIAL_STUDENT_RATE = 0.8d;

	@Override
	public LoanMetric getLoanMetric(Loan loan) {
		// Formula: 0.8 * (requestedAmount * monthlyInterestRate) / (1 - (1 + monthlyInterestRate)^((-1) * termMonths))
		// There's no need to multiply monthlyInterestRate by -1 simply negating the value achieves the same
		// BigDecimal is the recommended type for money use cases and financial transactions,
		// for the sake of simplicity I'll stick to Double values

		/*
		Even if we argue that the formula is similar to the ConsumerLoanMetricCalculator and we could abstract it to
		a utils class and just composite the different parts, I rather not overengineer and I think it's much better
		to keep things close and encapsulated here
		*/
		Double monthlyInterestRate = loan.getMonthlyInterestRate();
		double monthlyPayment = SPECIAL_STUDENT_RATE
				* (loan.getRequestedAmount() * monthlyInterestRate)
				/ (1 - (Math.pow(1 + monthlyInterestRate, -loan.getTermMonths())));

		return new LoanMetric(monthlyInterestRate, monthlyPayment);
	}

	@Override
	public boolean isSupported(Loan loan){
		/*
		I was tempted to combine the age calculation in the default method implementation
		but that would be exposing more details to the interface about the implementation
		of StudentLeanMetricCalculator, and that should be encapsulated here
		*/
		int borrowerAge = loan.getBorrower().getAge();
		return ILoanMetricCalculator.super.isSupported(loan)
				&& borrowerAge > 18
				&& borrowerAge < 30;
	}

}
