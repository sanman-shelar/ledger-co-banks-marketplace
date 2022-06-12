package com.lco.bm.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.lco.bm.service.LoanService;

public class LoanServiceImplTest {

    @Test
    public void addLoanTest() throws IOException {

        LoanService loanService = new LoanServiceImpl();
        loanService.add("LOAN IDIDI John 14000 2 8");
        loanService.add("LOAN IDIDI Sam 5000 1 6");

        var loan = loanService.getLoanUsingBankAndBorrower("IDIDI", "Sam");

        assertEquals("IDIDI", loan.getBankName());
        assertEquals("Sam", loan.getBorrowerName());
        assertEquals(5000, loan.getPrincipalAmount());
        assertEquals(1, loan.getYears());
        assertEquals(6, loan.getInterest());
    }

}
