package com.lco.bm.service.impl;

import java.util.List;
import java.util.UUID;

import com.lco.bm.config.AppData;
import com.lco.bm.model.Payment;
import com.lco.bm.service.PaymentService;
import com.lco.bm.util.Mapper;

public class PaymentServiceImpl implements PaymentService {

    public void add(String transaction) {
        var payment = Mapper.transactionToPayment(transaction, UUID.randomUUID());
        AppData.savePayment(payment);
    }

    public List<Payment> getPaymentsUsingLoanIdAndEMINumber(UUID loanId, Integer emiNumber) {
        return AppData.getPaymentsUsingLoanIdAndEMINumber(loanId, emiNumber);
    }

}
