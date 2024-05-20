package com.example.restservice.repository;

import com.example.restservice.model.Loan;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LoanRepository extends MongoRepository<Loan, String> {

    Optional<Loan> findByLoanId(Long loanId);

}
