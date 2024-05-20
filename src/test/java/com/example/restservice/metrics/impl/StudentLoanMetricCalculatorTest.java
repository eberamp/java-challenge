package com.example.restservice.metrics.impl;

import com.example.restservice.constant.LoanType;
import com.example.restservice.model.Loan;
import com.example.restservice.model.LoanMetric;
import com.example.restservice.util.LoanGeneratorUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentLoanMetricCalculatorTest {

    StudentLoanMetricCalculator calculator = new StudentLoanMetricCalculator();

    @Test
    void shouldGetCorrectLoanMetric() {
        Loan loan = LoanGeneratorUtil.createLoan();
        loan.setType(LoanType.STUDENT);
        loan.getBorrower().setAge(19);

        Double expectedMonthlyInterestRate = calculateMonthlyInterestRate(loan);
        Double expectedMonthlyPayment = calculateMonthlyPayment(loan);

        LoanMetric metric = calculator.getLoanMetric(loan);
        assertEquals(expectedMonthlyPayment, metric.getMonthlyPayment(), "Monthly payment should be equal to expected");
        assertEquals(expectedMonthlyInterestRate, metric.getMonthlyInterestRate(), "Monthly interest rate should be equal to expected");
    }

    @Test
    void isSupportedShouldReturnTrueWhenLoanTypeAndBorrowersAgeValid() {
        Loan loan = LoanGeneratorUtil.createLoan();
        loan.setType(LoanType.STUDENT);
        loan.getBorrower().setAge(19);

        assertTrue(calculator.isSupported(loan));
    }

    @Test
    void isSupportedShouldReturnFalseWhenLoanTypeIsConsumer() {
        Loan loan = LoanGeneratorUtil.createLoan();
        loan.setType(LoanType.CONSUMER);
        loan.getBorrower().setAge(19);

        assertFalse(calculator.isSupported(loan));
    }

    @Test
    void isSupportedShouldReturnFalseWhenBorrowerAgeIsNotValid() {
        Loan loan = LoanGeneratorUtil.createLoan();
        loan.setType(LoanType.STUDENT);

        loan.getBorrower().setAge(18);
        assertFalse(calculator.isSupported(loan));

        loan.getBorrower().setAge(30);
        assertFalse(calculator.isSupported(loan));
    }

    /*
    The following methods encapsulate the current algorithms to calculate monthlyInterestRate and monthlyPayment
    if a change occurred in the code we will know as the tests will fail.
    If the requirements change then we would have to update the algorithms here. this adds a bit of maintenance,
    but it's a trade off to have certainty of what should be the expected behaviour unless it changes
    */
    private double calculateMonthlyInterestRate(Loan loan){
        return (loan.getAnnualInterest() / 12) / 100;
    }

    private double calculateMonthlyPayment(Loan loan){
        Double monthlyInterestRate = calculateMonthlyInterestRate(loan);
        return 0.8
                * (loan.getRequestedAmount() * monthlyInterestRate)
                / (1 - (Math.pow(1 + monthlyInterestRate, -loan.getTermMonths())));
    }

}