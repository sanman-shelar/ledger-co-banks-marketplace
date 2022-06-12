package com.lco.bm.service.impl;

import com.lco.bm.model.BalanceOutput;
import com.lco.bm.service.BalanceService;
import com.lco.bm.service.LoanService;
import com.lco.bm.service.PaymentService;
import com.lco.bm.util.Mapper;

public class BalanceServiceImpl implements BalanceService {

    private LoanService loanService;
    private PaymentService paymentService;

    public BalanceServiceImpl(LoanService loanService, PaymentService paymentService) {
        this.loanService = loanService;
        this.paymentService = paymentService;
    }

    public void calculateAmountPaid(String transaction) {
        BalanceOutput balanceOutput;

        var balanceTransaction = Mapper.trasactionToBalanceTransaction(transaction);
        var loan = loanService.getLoanUsingBankAndBorrower(balanceTransaction.getBankName(), balanceTransaction.getBorrowerName());
        var payments = paymentService.getPaymentsUsingLoanIdAndEMINumber(loan.getId(), balanceTransaction.getEmiNumber());

        var totalInterestAmount = ((loan.getPrincipalAmount() * loan.getInterest()) / 100) * loan.getYears();
        var totalAmountToRepay = Double.valueOf(loan.getPrincipalAmount() + totalInterestAmount);
        var totalEMICount = Double.valueOf(loan.getYears() * 12);

        var emiAmount = Math.ceil(totalAmountToRepay / totalEMICount);

        var totalEMIPaidAmount = Double.valueOf(emiAmount * balanceTransaction.getEmiNumber());

        if (payments.size() == 0) {
            var remainingEMI = totalEMICount.intValue() - balanceTransaction.getEmiNumber();
            balanceOutput = new BalanceOutput(loan.getBankName(), loan.getBorrowerName(), totalEMIPaidAmount.intValue(), remainingEMI);
        } else {
            var totalLumpSumPaidAmount = payments.stream().mapToInt(payment -> payment.getAmount()).sum();
            var totalAmountPaid = totalEMIPaidAmount.intValue() + totalLumpSumPaidAmount;
            var remainingAmount = totalAmountToRepay - totalAmountPaid;
            var remainingEMI = Math.ceil(remainingAmount / emiAmount);
            // var finalEMIAmount = remainingAmount - (emiAmount * (remainingEMI-1));
            balanceOutput = new BalanceOutput(loan.getBankName(), loan.getBorrowerName(), totalAmountPaid, (int) remainingEMI);
        }

        System.out.println(balanceOutput);

    }
}
