package com.example.restservice.util;

import com.example.restservice.constant.LoanType;
import com.example.restservice.model.Borrower;
import com.example.restservice.model.Loan;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoanGeneratonUtil {

	public static Loan createLoan(Long loanId) {
		LoanType loanType = loanId % 2 == 0 ? LoanType.STUDENT : LoanType.CONSUMER;
		Borrower borrower = new Borrower();
		borrower.setName("Borrower ".concat(loanId.toString()));
		borrower.setAge(new Random().nextInt(82) + 18);
		borrower.setAnnualIncome(150000D);
		borrower.setDelinquentDebt(loanId % 2 == 0);
		borrower.setAnnualDebt(1200D);
		borrower.setCreditHistory(new Random().nextInt(50) + 1);

		Loan loan = new Loan();
		loan.setLoanId(loanId);
		loan.setRequestedAmount(1000D * loanId);
		loan.setTermMonths(loanId % 2 == 0 ? 36 : 60);
		loan.setAnnualInterest(0.2d * (loanId / (loanId + 1.0d)));
		loan.setType(loanType);
		loan.setBorrower(borrower);

		return loan;
	}
	
	public static List<Loan> getRandomLoans(Long numberOfLoans) {
		List<Loan> loans = new ArrayList<Loan>();
		for (Integer x = 1; x <= numberOfLoans; x++) {
			loans.add(createLoan(Long.valueOf(x)));
		}
		return loans;
	}
}
