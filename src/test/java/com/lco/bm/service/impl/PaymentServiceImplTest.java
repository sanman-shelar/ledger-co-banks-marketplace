package com.lco.bm.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.lco.bm.service.LoanService;
import com.lco.bm.service.PaymentService;

public class PaymentServiceImplTest {

    @Test
    public void addPaymentTest() throws IOException {

        LoanService loanService = new LoanServiceImpl();
        loanService.add("LOAN IDIDI Jack 5000 1 6");

        PaymentService paymentService = new PaymentServiceImpl();

        paymentService.add("PAYMENT IDIDI Jack 1000 5");
        paymentService.add("PAYMENT IDIDI Jack 500 6");

        var loan = loanService.getLoanUsingBankAndBorrower("IDIDI", "Jack");
        var payments = paymentService.getPaymentsUsingLoanIdAndEMINumber(loan.getId(), 6);

        assertEquals(2, payments.size());

        assertEquals("IDIDI", loan.getBankName());
        assertEquals("Jack", loan.getBorrowerName());
        assertEquals(1000, payments.get(0).getAmount());
        assertEquals(5, payments.get(0).getEmiNumber());

        assertEquals(500, payments.get(1).getAmount());
    }

}
