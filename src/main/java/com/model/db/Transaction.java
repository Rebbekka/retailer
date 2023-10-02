package com.model.db;

import java.util.Date;

public class Transaction {
    String ID;
    Double dollarsSpent;
    Date transactionDate;

    public Transaction(String id, Double dollarsSpent, Date transactionDate) {
        this.ID = id;
        this.dollarsSpent = dollarsSpent;
        this.transactionDate = transactionDate;
    }

    public String getID() {
        return this.ID;
    }

    public void setID(String id) {
        this.ID = id;
    }

    public Double getDollarsSpent() {
        return this.dollarsSpent;
    }

    public void setDollarsSpent(Double dollarsSpent) {
        this.dollarsSpent = dollarsSpent;
    }

    public Date getTransactionDate() {
        return this.transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}
