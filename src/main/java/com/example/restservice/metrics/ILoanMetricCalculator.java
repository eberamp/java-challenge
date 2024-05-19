package com.example.restservice.metrics;

import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;

public interface ILoanMetricCalculator {

	/**
	 * Validates if a loan is supported to calculate metrics
	 * 
	 * @param Loan
	 */
	// I'm unsure if isSupported should check for the currently supported types, or it is to mean
	// if the calculator supports the loan given type, I went with the general approach.
	// If there are any special considerations for each calculator they should override it in their
	// own implementation
	default boolean isSupported(Loan loan) {
		// Validate if the loan type is supported
		switch (loan.getType()){
			case CONSUMER:
            case STUDENT:
                return true;
            default:
				return false;
		}
	}

	/**
	 * Calculates the Loan Metric of a Loan entity
	 * 
	 * @param loan
	 */
    LoanMetric getLoanMetric(Loan loan);

}
