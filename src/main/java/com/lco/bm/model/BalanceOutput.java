package com.lco.bm.model;

public class BalanceOutput {
    private String bankName;
    private String borrowerName;
    private Integer totalPaidAmount;
    private Integer remainingEMICount;

    public BalanceOutput(String bankName, String borrowerName, Integer totalPaidAmount, Integer remainingEMICount) {
        this.bankName = bankName;
        this.borrowerName = borrowerName;
        this.totalPaidAmount = totalPaidAmount;
        this.remainingEMICount = remainingEMICount;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public Integer getTotalPaidAmount() {
        return totalPaidAmount;
    }

    public Integer getRemainingEMICount() {
        return remainingEMICount;
    }

    public String toString() {
        return bankName + " " + borrowerName + " " + totalPaidAmount + " " + remainingEMICount;
    }

}
