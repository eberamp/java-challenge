package com.example.restservice.configuration;

import com.example.restservice.constant.LoanType;
import com.example.restservice.metrics.ILoanMetricCalculator;
import com.example.restservice.metrics.impl.ConsumerLoanMetricCalculator;
import com.example.restservice.metrics.impl.StudentLoanMetricCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class MetricCalculatorConfiguration {

    @Autowired
    private final List<ILoanMetricCalculator> calculators;

    @Bean(name = "metricCalculators")
    public HashMap<LoanType, ILoanMetricCalculator> mapMetricCalculators() {
        HashMap<LoanType, ILoanMetricCalculator> map = new HashMap<>();

        // In case more LoanTypes are added and require to use a different or the same calculator
        // we can map each implementation to a type here
        for (ILoanMetricCalculator calculator : calculators) {
            if (calculator instanceof ConsumerLoanMetricCalculator) {
                map.put(LoanType.CONSUMER, calculator);
            } else if (calculator instanceof StudentLoanMetricCalculator) {
                map.put(LoanType.STUDENT, calculator);
            }
        }

        return map;
    }

}
