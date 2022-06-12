package com.lco.bm.service;

import java.util.List;
import java.util.UUID;

import com.lco.bm.model.Payment;

public interface PaymentService {

    public void add(String transaction);
    
    public List<Payment> getPaymentsUsingLoanIdAndEMINumber(UUID loanId, Integer emiNumber);
}
