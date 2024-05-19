package com.example.restservice.controller;

import com.example.restservice.service.ILoanService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.Optional;

@RestController("/loans")
public class LoanController {

	private final ILoanService loanService;

	@Autowired
	public LoanController(
			ILoanService loanService
	){
		this.loanService = loanService;
	}

	@GetMapping("/{id}")
	@ExceptionHandler()
	public ResponseEntity<Loan> getLoan(@PathParam("id") Long loanId) {

		Optional<Loan> loan = loanService.findLoanById(loanId);
        return ResponseEntity.of(loan);

    }

	@GetMapping("metrics/{id}")
	public ResponseEntity<LoanMetric> calculateLoanMetric(@PathParam("id") @Valid Long loanId) {

		Optional<LoanMetric> metric = loanService.calculateLoanMetric(loanId);
		return ResponseEntity.of(metric);

	}

	@GetMapping("/metrics")
	public ResponseEntity<LoanMetric> calculateLoanMetric(@RequestBody @Valid Loan loan) {

		Optional<LoanMetric> metric = loanService.calculateLoanMetric(loan);
		return ResponseEntity.of(metric);

	}

	@GetMapping("/search")
	public ResponseEntity<Loan> getMaxMonthlyPaymentLoan(
			@RequestParam("sort") String sort
	) {
		// Call LoanService
		return null;
	}

}
