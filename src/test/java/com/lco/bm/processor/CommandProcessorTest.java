package com.lco.bm.processor;

import static org.mockito.Mockito.times;

import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.lco.bm.service.BalanceService;
import com.lco.bm.service.LoanService;
import com.lco.bm.service.PaymentService;

public class CommandProcessorTest {

    @Mock
    LoanService loanService;

    @Mock
    PaymentService paymentService;

    @Mock
    BalanceService balanceService;

    CommandProcessor commandProcessor;

    @BeforeEach
    public void init() throws IOException {
        MockitoAnnotations.openMocks(this);

        Mockito.doNothing().when(loanService).add(Mockito.anyString());
        Mockito.doNothing().when(paymentService).add(Mockito.anyString());
        Mockito.doNothing().when(balanceService).calculateAmountPaid(Mockito.anyString());

        commandProcessor = new CommandProcessor(loanService, paymentService, balanceService);
    }

    @Test
    public void verifyCommandIsLoan() {

        commandProcessor.process(Arrays.asList("LOAN IDIDI Sam 10000 5 4", "LOAN IDIDI Joe 20000 1 4"));

        Mockito.verify(loanService, times(2)).add(Mockito.anyString());
        Mockito.verify(paymentService, times(0)).add(Mockito.anyString());
        Mockito.verify(balanceService, times(0)).calculateAmountPaid(Mockito.anyString());
    }

    @Test
    public void verifyCommandIsPayment() {

        commandProcessor.process(Arrays.asList("PAYMENT MBI Harry 5000 10"));

        Mockito.verify(loanService, times(0)).add(Mockito.anyString());
        Mockito.verify(paymentService, times(1)).add(Mockito.anyString());
        Mockito.verify(balanceService, times(0)).calculateAmountPaid(Mockito.anyString());
    }

    @Test
    public void verifyCommandIsBalance() {

        commandProcessor.process(Arrays.asList("BALANCE MBI Harry 12", "BALANCE MBI Harry 1", "BALANCE MBI Harry 4"));

        Mockito.verify(loanService, times(0)).add(Mockito.anyString());
        Mockito.verify(paymentService, times(0)).add(Mockito.anyString());
        Mockito.verify(balanceService, times(3)).calculateAmountPaid(Mockito.anyString());
    }
    
    @Test
    public void verifyCommandIsInvalid() {

        commandProcessor.process(Arrays.asList("BAL MBI Harry 12"));

        Mockito.verify(loanService, times(0)).add(Mockito.anyString());
        Mockito.verify(paymentService, times(0)).add(Mockito.anyString());
        Mockito.verify(balanceService, times(0)).calculateAmountPaid(Mockito.anyString());
    }
}
