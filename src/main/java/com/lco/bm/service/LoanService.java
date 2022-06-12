package com.lco.bm.service;

import com.lco.bm.model.Loan;

public interface LoanService {

    public void add(String transaction);
    
    public Loan getLoanUsingBankAndBorrower(String bankName, String borrowerName);
}
