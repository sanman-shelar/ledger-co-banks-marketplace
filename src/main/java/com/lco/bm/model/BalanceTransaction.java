package com.lco.bm.model;

public class BalanceTransaction {
    private String bankName;
    private String borrowerName;
    private Integer emiNumber;

    public BalanceTransaction(String bankName, String borrowerName, Integer emiNumber) {
        this.bankName = bankName;
        this.borrowerName = borrowerName;
        this.emiNumber = emiNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public Integer getEmiNumber() {
        return emiNumber;
    }

}
