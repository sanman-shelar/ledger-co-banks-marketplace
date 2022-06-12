package com.lco.bm.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.lco.bm.model.Loan;
import com.lco.bm.model.Payment;
import com.lco.bm.service.LoanService;
import com.lco.bm.service.PaymentService;

public class BalanceServiceImplTest {

    private ByteArrayOutputStream bo;

    @Mock
    LoanService loanService;

    @Mock
    PaymentService paymentService;

    @BeforeEach
    public void init() throws IOException {
        MockitoAnnotations.openMocks(this);

        bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        bo.flush();
    }

    @Test
    public void getBalanceForNoPaymentsTest() {

        when(loanService.getLoanUsingBankAndBorrower(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(new Loan(UUID.randomUUID(), "IDIDI", "Joe", 5000, 1, 6));

        when(paymentService.getPaymentsUsingLoanIdAndEMINumber(Mockito.any(),
                Mockito.anyInt())).thenReturn(Arrays.asList());

        new BalanceServiceImpl(loanService, paymentService).calculateAmountPaid("BALANCE IDIDI Joe 3");

        String allWrittenLinesToConsole = new String(bo.toByteArray()).trim();
        assertEquals("IDIDI Joe 1326 9", allWrittenLinesToConsole);

    }

    @Test
    public void getBalanceForPaymentsTest() throws IOException {

        var loanId = UUID.randomUUID();

        when(loanService.getLoanUsingBankAndBorrower(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(new Loan(loanId, "IDIDI", "Joe", 5000, 1, 6));

        when(paymentService.getPaymentsUsingLoanIdAndEMINumber(Mockito.any(),
                Mockito.anyInt())).thenReturn(Arrays.asList(new Payment(UUID.randomUUID(), loanId, 1000, 5)));

        new BalanceServiceImpl(loanService, paymentService).calculateAmountPaid("BALANCE IDIDI Dale 6");

        String allWrittenLinesToConsole = new String(bo.toByteArray()).trim();
        assertEquals("IDIDI Joe 3652 4", allWrittenLinesToConsole);

    }

}
