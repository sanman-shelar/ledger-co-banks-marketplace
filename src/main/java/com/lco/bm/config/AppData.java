package com.lco.bm.config;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.lco.bm.model.Loan;
import com.lco.bm.model.Payment;

public class AppData {

    private static List<Loan> loans = new ArrayList<>();
    private static List<Payment> payments = new ArrayList<>();

    public static void saveLoan(Loan loan) {
        loans.add(loan);
    }

    public static Loan getLoanUsingBankAndBorrower(String bankName, String borrowerName) {
        var loan = loans.stream().filter(l -> l.getBankName().equals(bankName) && l.getBorrowerName().equals(borrowerName)).findFirst();
        return loan.orElse(null);
    }

    public static void savePayment(Payment payment) {
        payments.add(payment);
    }

    public static List<Payment> getPaymentsUsingLoanIdAndEMINumber(UUID loanId, Integer emiNumber) {
        return payments.stream().filter(payment -> payment.getLoanId().equals(loanId) && payment.getEmiNumber() <= emiNumber)
                .collect(Collectors.toList());
    }

}
