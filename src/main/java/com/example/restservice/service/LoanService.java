package com.example.restservice.service;

import java.util.List;
import java.util.Optional;

import com.example.restservice.exception.LoanNotFoundException;
import com.example.restservice.exception.UnsupportedLoanTypeException;
import com.example.restservice.metrics.ILoanMetricCalculator;
import com.example.restservice.metrics.LoanMetricFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;
import com.example.restservice.util.LoanGeneratonUtil;

@Service
@RequiredArgsConstructor
public class LoanService implements ILoanService {

	private LoanMetricFactory loanMetricFactory;

	public Optional<Loan> findLoanById(Long id) {
		return Optional.of(LoanGeneratonUtil.createLoan(id));
	}

	public Optional<LoanMetric> calculateLoanMetric(Loan loan) {
		// Use the LoanMetricFactory based on the loan type
		LoanMetric metric = getLoanMetric(loan);
		return Optional.of(metric);
	}

	public Optional<LoanMetric> calculateLoanMetric(Long loanId) {
		Loan loan = findLoanById(loanId).orElseThrow(
				() -> new LoanNotFoundException("Loan not found " + loanId)
		);
		LoanMetric metric = getLoanMetric(loan);
		return Optional.of(metric);
	}

	public Optional<Loan> getMaxMonthlyPaymentLoan() {
		//
		List<Loan> allLoans = LoanGeneratonUtil.getRandomLoans(20L);

		return null;
	}

	private LoanMetric getLoanMetric(Loan loan){
		ILoanMetricCalculator calculator = loanMetricFactory.getCalculatorForLoanType(loan.getType());
		if(calculator.isSupported(loan)){
			return calculator.getLoanMetric(loan);
		} else {
			throw new UnsupportedLoanTypeException("Loan type not supported");
		}
	}
}
