package com.example.restservice.service;

import com.example.restservice.constant.LoanType;
import com.example.restservice.exception.LoanNotFoundException;
import com.example.restservice.exception.UnsupportedLoanTypeException;
import com.example.restservice.metrics.ILoanMetricCalculator;
import com.example.restservice.metrics.LoanMetricFactory;
import com.example.restservice.metrics.impl.ConsumerLoanMetricCalculator;
import com.example.restservice.metrics.impl.StudentLoanMetricCalculator;
import com.example.restservice.model.Loan;
import com.example.restservice.repository.LoanRepository;
import com.example.restservice.util.LoanGeneratonUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

    @Mock
    private LoanMetricFactory loanMetricFactory;

    @Mock
    private ILoanMetricCalculator calculator;

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanService service;

    @Spy
    @InjectMocks
    private LoanService serviceSpy;

    @Test
    void calculateLoanMetricShouldThrowUnsupportedLoanTypeException() {
        Mockito.when(loanMetricFactory.getCalculatorForLoanType(any(LoanType.class)))
                .thenReturn(calculator);

        Mockito.when(calculator.isSupported(any(Loan.class)))
                .thenReturn(false);

        Loan loan = LoanGeneratonUtil.createLoan();
        assertThrows(UnsupportedLoanTypeException.class, () -> service.calculateLoanMetric(loan));
    }

    @Test
    void calculateLoanMetricShouldThrowLoanNotFoundException() {
        Mockito.when(loanRepository.findById(any(String.class)))
                .thenReturn(Optional.empty());

        Long nonExistentLoanId = 1234L;
        assertThrows(LoanNotFoundException.class, () -> serviceSpy.calculateLoanMetric(nonExistentLoanId));
    }

    @Test
    void getMaxMonthlyPaymentLoan() {
        // We could argue this gets closer to a integration test but the code under test involves the logic of the calculators
        // if we were to mock the result of getLoanMetric and hence the calculations then what point is there to the test
        Mockito.when(loanMetricFactory.getCalculatorForLoanType(LoanType.STUDENT))
                .thenReturn(new StudentLoanMetricCalculator());

        Mockito.when(loanMetricFactory.getCalculatorForLoanType(LoanType.CONSUMER))
                .thenReturn(new ConsumerLoanMetricCalculator());

        List<Loan> loans = service.generateAllLoans();

        // Usually the loan that will pay the most a month is the one that has a high requested amount with high interest rate
        // and lower month term, doing it this way for simplicity and avoiding having to manually set up each Loan
        Optional<Loan> highestPayingLoan = loans.stream()
                .max(Comparator
                        .comparing(Loan::getTermMonths, Comparator.reverseOrder())
                        .thenComparing(Loan::getRequestedAmount)
                        .thenComparing(Loan::getAnnualInterest));

        Mockito.when(serviceSpy.getAllLoans()).thenReturn(loans);

        Optional<Loan> result = serviceSpy.getMaxMonthlyPaymentLoan();
        assertTrue(highestPayingLoan.isPresent());
        assertTrue(result.isPresent());
        assertEquals(highestPayingLoan.get(), result.get());
    }

}