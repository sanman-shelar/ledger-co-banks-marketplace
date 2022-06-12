package com.lco.bm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.lco.bm.processor.CommandProcessor;
import com.lco.bm.service.BalanceService;
import com.lco.bm.service.LoanService;
import com.lco.bm.service.PaymentService;
import com.lco.bm.service.impl.BalanceServiceImpl;
import com.lco.bm.service.impl.LoanServiceImpl;
import com.lco.bm.service.impl.PaymentServiceImpl;

public class App {

    public static void main(String[] args) {

        try {
            var inputData = Files.readAllLines(Paths.get(args[0]));

            LoanService loanService = new LoanServiceImpl();
            PaymentService paymentService = new PaymentServiceImpl();
            BalanceService balanceService = new BalanceServiceImpl(loanService, paymentService);

            new CommandProcessor(loanService, paymentService, balanceService).process(inputData);

        } catch (IOException e) {
            System.out.println("Error while loading the provided file");
        }
    }
}
