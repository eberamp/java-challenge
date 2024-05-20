package com.example.restservice.util;

import com.example.restservice.constant.LoanType;
import com.example.restservice.model.Borrower;
import com.example.restservice.model.Loan;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoanGeneratonUtil {

	public static Loan createLoan() {

		ObjectId id = new ObjectId();
		int randomSeed = id.toHexString().chars().sum();
		long count = id.toHexString().chars().distinct().count();
		long average = (long) id.toHexString().chars().average().orElse(10.10);
		long loanId = (long) id.getTimestamp() * randomSeed / (count * average);

		LoanType loanType = loanId % 2 == 0 ? LoanType.STUDENT : LoanType.CONSUMER;

		Borrower borrower = new Borrower();
		borrower.setName("Borrower ".concat(Long.toString(loanId)));
		borrower.setAge(new Random().nextInt(82) + 18);
		borrower.setAnnualIncome(150000D);
		borrower.setDelinquentDebt(loanId % 2 == 0);
		borrower.setAnnualDebt(1200D);
		borrower.setCreditHistory(new Random().nextInt(50) + 1);

		Loan loan = new Loan();
		loan.setLoanId(loanId);
		loan.setRequestedAmount((double) (loanId / randomSeed) / (count * average) * (randomSeed / count));
		loan.setTermMonths(loanId % 2 == 0 ? 36 : 60);
		loan.setAnnualInterest( ((double) randomSeed / average / count));
		loan.setLoanOfficerId(loanId + 1);
		loan.setType(loanType);
		loan.setBorrower(borrower);

		return loan;
	}
	
	public static List<Loan> getRandomLoans(Long numberOfLoans) {
		List<Loan> loans = new ArrayList<>();
		for (Integer x = 1; x <= numberOfLoans; x++) {
			loans.add(createLoan());
		}
		return loans;
	}
}
