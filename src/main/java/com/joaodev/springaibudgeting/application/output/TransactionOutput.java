package com.joaodev.springaibudgeting.application.output;

import com.joaodev.springaibudgeting.domain.Transaction;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record TransactionOutput(String id, String description, String category, double amount) {
    public static TransactionOutput from(Transaction transaction) {
        return new TransactionOutput(
                transaction.id().uuid().toString(),
                transaction.description(),
                transaction.category().name(),
                BigDecimal.valueOf(transaction.amount())
                        .setScale(2, RoundingMode.HALF_UP).doubleValue());
    }
}
