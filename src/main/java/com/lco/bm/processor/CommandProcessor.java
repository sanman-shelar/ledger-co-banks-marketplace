package com.lco.bm.processor;

import java.util.List;

import com.lco.bm.service.BalanceService;
import com.lco.bm.service.LoanService;
import com.lco.bm.service.PaymentService;

public class CommandProcessor {

    private LoanService loanService;
    private PaymentService paymentService;
    private BalanceService balanceService;

    public CommandProcessor(LoanService loanService, PaymentService paymentService, BalanceService balanceService) {
        this.loanService = loanService;
        this.paymentService = paymentService;
        this.balanceService = balanceService;
    }

    public void process(List<String> transasctions) {

        for (String transaction : transasctions) {

            var command = getCommandFromTransaction(transaction);

            switch (command) {
            case "LOAN":
                loanService.add(transaction);
                break;

            case "PAYMENT":
                paymentService.add(transaction);
                break;

            case "BALANCE":
                balanceService.calculateAmountPaid(transaction);
                break;

            default:
                System.out.println("Invalid command: " + transaction);

            }
        }
    }

    private static String getCommandFromTransaction(String transaction) {
        return transaction.split(" ")[0].trim();
    }
}
