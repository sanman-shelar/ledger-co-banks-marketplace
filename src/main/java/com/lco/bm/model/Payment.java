package com.lco.bm.model;

import java.util.UUID;

public class Payment {
    private UUID id;
    private UUID loanId;
    private Integer amount;
    private Integer emiNumber;

    public Payment(UUID id, UUID loanId, Integer amount, Integer emiNumber) {
        super();
        this.id = id;
        this.loanId = loanId;
        this.amount = amount;
        this.emiNumber = emiNumber;
    }

    public UUID getId() {
        return id;
    }

    public UUID getLoanId() {
        return loanId;
    }

    public Integer getAmount() {
        return amount;
    }

    public Integer getEmiNumber() {
        return emiNumber;
    }

}
