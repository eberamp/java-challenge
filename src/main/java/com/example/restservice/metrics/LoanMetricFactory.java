package com.example.restservice.metrics;

import com.example.restservice.constant.LoanType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restservice.model.Loan;

import java.util.HashMap;

/*
   I would change this to be a Strategy rather than a Factory, although both patterns
   are similar I think the Strategy fits better the purpose of choosing an implementation
   for calculating the LoanMetric based of the loan type
*/

@Service
@RequiredArgsConstructor
public class LoanMetricFactory {

    private final HashMap<LoanType, ILoanMetricCalculator> metricCalculators;

    // Renamed the method to make it more representative of what it does instead of simply getInstance
    public ILoanMetricCalculator getCalculatorForLoanType(LoanType loanType) {
        return metricCalculators.get(loanType);
    }

}
