package com.example.restservice.service;

import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;

import java.util.List;
import java.util.Optional;

public interface ILoanService {

    Optional<Loan> findLoanById(Long id);
    List<Loan> getAllLoans();
    Optional<LoanMetric> calculateLoanMetric(Long loanId);
    Optional<LoanMetric> calculateLoanMetric(Loan loan);
    Optional<Loan> getMaxMonthlyPaymentLoan();
    Optional<Loan> saveLoan();

}
