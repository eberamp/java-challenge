package com.example.restservice.controller;

import com.example.restservice.exception.LoanNotFoundException;
import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;
import com.example.restservice.service.ILoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api") // decided to change the base path just as a preference
public class LoanController {

	private final ILoanService loanService;

	// Constructor could be replaced with Lomboks RequiredArgsConstructor, but I prefer to have more control of the
	// dependencies in the controllers and make them more visible, so I set them explicitly
	@Autowired
	public LoanController(
			ILoanService loanService
	){
		this.loanService = loanService;
	}

	@GetMapping("/loans/generate")
	public ResponseEntity<Loan> saveLoan() {

		Optional<Loan> loan = loanService.saveLoan();
		// Need better error handling with appropriate status response
		return ResponseEntity.of(loan);

	}

	@GetMapping("/loans/{id}")
	public ResponseEntity<Loan> getLoan(@PathVariable("id") String id) {

		Long loanId = Long.parseLong(id);
		Optional<Loan> loan = loanService.findLoanById(loanId);

		if(!loan.isPresent()){
			throw new LoanNotFoundException(loanId);
		}

		return ResponseEntity.of(loan);
    }

	@GetMapping("/loans/all")
	public ResponseEntity<List<Loan>> getAllLoans() {

		List<Loan> loans = loanService.getAllLoans();
		if(loans.isEmpty()){
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(loans);

	}

	@GetMapping("/metrics/{id}")
	public ResponseEntity<LoanMetric> calculateLoanMetric(@PathVariable("id") String id) {

		Long loanId = Long.parseLong(id);
		Optional<LoanMetric> metric = loanService.calculateLoanMetric(loanId);
		return ResponseEntity.of(metric);

	}

	@GetMapping("/metrics")
	public ResponseEntity<LoanMetric> calculateLoanMetric(@RequestBody @Valid Loan loan) {

		Optional<LoanMetric> metric = loanService.calculateLoanMetric(loan);
		return ResponseEntity.of(metric);

	}

	/*
		For this endpoint we could do a RequestParam for a variable `sort` and accept `asc` or `desc` and limit the result to 1
		we could also make it a RequestBody and have more control over the validation and data needed for a more thorough search
	*/
	@GetMapping("loans/find/max-monthly-payment")
	public ResponseEntity<Loan> getMaxMonthlyPaymentLoan() {

		Optional<Loan> maxMonthlyPaymentLoan = loanService.getMaxMonthlyPaymentLoan();
		return ResponseEntity.of(maxMonthlyPaymentLoan);

	}

}
