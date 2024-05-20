package com.example.restservice.metrics;

import com.example.restservice.constant.LoanType;
import com.example.restservice.metrics.impl.ConsumerLoanMetricCalculator;
import com.example.restservice.metrics.impl.StudentLoanMetricCalculator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LoanMetricFactoryTest {

    @Mock
    private HashMap<LoanType, ILoanMetricCalculator> calculators;

    @InjectMocks
    private LoanMetricFactory loanMetricFactory;

    @Test
    void getCalculatorForLoanTypeConsumer() {
        Mockito.when(calculators.get(LoanType.CONSUMER)).thenReturn(new ConsumerLoanMetricCalculator());
        ILoanMetricCalculator calculator = loanMetricFactory.getCalculatorForLoanType(LoanType.CONSUMER);
        assertTrue(calculator instanceof ConsumerLoanMetricCalculator);
    }

    @Test
    void getCalculatorForLoanTypeStudent() {
        Mockito.when(calculators.get(LoanType.STUDENT)).thenReturn(new StudentLoanMetricCalculator());
        ILoanMetricCalculator calculator = loanMetricFactory.getCalculatorForLoanType(LoanType.STUDENT);
        assertTrue(calculator instanceof StudentLoanMetricCalculator);
    }
}