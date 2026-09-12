package com.joaodev.springaibudgeting.domain;

import lombok.Getter;

@Getter
public class Transaction {
    private final TransactionId id;
    private final String description;
    private final double amount;
    private final Category category;

    public Transaction(String description, double amount, Category category) {
        this.id = new TransactionId();
        this.description = description;
        this.amount = amount;
        this.category = category;
    }
}
