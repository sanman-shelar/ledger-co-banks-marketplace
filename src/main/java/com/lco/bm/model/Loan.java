package com.lco.bm.model;

import java.util.UUID;

public class Loan {

    private UUID id;
    private String bankName;
    private String borrowerName;
    private Integer principalAmount;
    private Integer years;
    private Integer interest;

    public Loan(UUID id, String bankName, String borrowerName, Integer principalAmount, Integer years, Integer interest) {
        this.id = id;
        this.bankName = bankName;
        this.borrowerName = borrowerName;
        this.principalAmount = principalAmount;
        this.years = years;
        this.interest = interest;
    }

    public UUID getId() {
        return id;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public Integer getPrincipalAmount() {
        return principalAmount;
    }

    public Integer getYears() {
        return years;
    }

    public Integer getInterest() {
        return interest;
    }

}
