package com.lco.bm.util;

import java.util.UUID;

import com.lco.bm.config.AppData;
import com.lco.bm.model.BalanceTransaction;
import com.lco.bm.model.Loan;
import com.lco.bm.model.Payment;

public class Mapper {

    public static Loan transactionToLoan(String transaction, UUID id) {
        var data = transaction.split(" ");
        return new Loan(
                id,
                data[1],
                data[2],
                Integer.parseInt(data[3]),
                Integer.parseInt(data[4]),
                Integer.parseInt(data[5]));
    }

    public static Payment transactionToPayment(String transaction, UUID id) {
        var data = transaction.split(" ");
        var loan = AppData.getLoanUsingBankAndBorrower(data[1], data[2]);
        return new Payment(
                id,
                loan.getId(),
                Integer.parseInt(data[3]),
                Integer.parseInt(data[4]));
    }

    public static BalanceTransaction trasactionToBalanceTransaction(String transaction) {
        var data = transaction.split(" ");
        return new BalanceTransaction(data[1], data[2], Integer.parseInt(data[3]));
    }

}
