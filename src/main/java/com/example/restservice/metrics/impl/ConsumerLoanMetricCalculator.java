package com.example.restservice.metrics.impl;

import com.example.restservice.constant.LoanType;
import org.springframework.stereotype.Component;

import com.example.restservice.metrics.ILoanMetricCalculator;
import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;

@Component()
public class ConsumerLoanMetricCalculator implements ILoanMetricCalculator {

	@Override
	public LoanMetric getLoanMetric(Loan loan) {
		// Formula: (requestedAmount * monthlyInterestRate) / (1 - (1 + monthlyInterestRate)^((-1) * termMonths))
		// There's no need to multiply monthlyInterestRate by -1 simply negating the value achieves the same
		// BigDecimal is the recommended type for money use cases and financial transactions,
		// for the sake of simplicity I'll stick to Double values

		Double monthlyInterestRate = loan.getMonthlyInterestRate();
		double monthlyPayment = (loan.getRequestedAmount() * monthlyInterestRate) / (1 - (Math.pow(1 + monthlyInterestRate, -loan.getTermMonths())));
        return new LoanMetric(monthlyInterestRate, monthlyPayment);
	}

}
