package com.lco.bm.service.impl;

import java.util.UUID;

import com.lco.bm.config.AppData;
import com.lco.bm.model.Loan;
import com.lco.bm.service.LoanService;
import com.lco.bm.util.Mapper;

public class LoanServiceImpl implements LoanService {

    public void add(String transaction) {
        var loan = Mapper.transactionToLoan(transaction, UUID.randomUUID());
        AppData.saveLoan(loan);
    }

    public Loan getLoanUsingBankAndBorrower(String bankName, String borrowerName) {
        return AppData.getLoanUsingBankAndBorrower(bankName, borrowerName);
    }
}
