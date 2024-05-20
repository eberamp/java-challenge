package com.example.restservice.service;

import com.example.restservice.exception.LoanNotFoundException;
import com.example.restservice.exception.UnsupportedLoanTypeException;
import com.example.restservice.metrics.ILoanMetricCalculator;
import com.example.restservice.metrics.LoanMetricFactory;
import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;
import com.example.restservice.repository.LoanRepository;
import com.example.restservice.util.LoanGeneratorUtil;
import com.mongodb.MongoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanService implements ILoanService {

	private final LoanMetricFactory loanMetricFactory;
	private final LoanRepository loanRepository;

	public Optional<Loan> findLoanById(Long id) {
		return loanRepository.findByLoanId(id);
	}

	public List<Loan> getAllLoans(){
		return loanRepository.findAll();
	}

	public List<Loan> generateAllLoans(){
		return LoanGeneratorUtil.getRandomLoans(20L);
	}

	public Optional<Loan> saveLoan(){
		Loan loan = LoanGeneratorUtil.createLoan();

		try {
			loanRepository.save(loan);
		} catch (MongoException e){
			// Log error and/or rethrow a new Exception to let the controller know something went wrong
			return Optional.empty();
		}

		return Optional.of(loan);
	}

	public Optional<LoanMetric> calculateLoanMetric(Loan loan) {
		// Use the LoanMetricFactory based on the loan type
		LoanMetric metric = getLoanMetric(loan);
		return Optional.of(metric);
	}

	public Optional<LoanMetric> calculateLoanMetric(Long loanId) {
		Loan loan = findLoanById(loanId).orElseThrow(
				() -> new LoanNotFoundException(loanId)
		);
		LoanMetric metric = getLoanMetric(loan);
		return Optional.of(metric);
	}

	public Optional<Loan> getMaxMonthlyPaymentLoan() {
		List<Loan> allLoans = this.getAllLoans();
		return allLoans.stream()
				.max(Comparator.comparingDouble( loan ->
						loanMetricFactory.getCalculatorForLoanType(loan.getType())
								.getLoanMetric(loan)
								.getMonthlyPayment())
				);
	}

	private LoanMetric getLoanMetric(Loan loan){
		ILoanMetricCalculator calculator = loanMetricFactory.getCalculatorForLoanType(loan.getType());
		if(calculator.isSupported(loan)){
			return calculator.getLoanMetric(loan);
		} else {
			throw new UnsupportedLoanTypeException("Loan type not supported: " + loan.getType().getLabel());
		}
	}
}
