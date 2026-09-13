package com.joaodev.springaibudgeting.infrastructure.http.response;

import com.joaodev.springaibudgeting.application.output.TransactionOutput;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record TransactionResponse(String id, String category, String description, double amount) {
    public static TransactionResponse from(TransactionOutput output) {
        return new TransactionResponse(
                output.id(),
                output.category(),
                output.description(),
                BigDecimal.valueOf(output.amount()).setScale(2, RoundingMode.HALF_UP).doubleValue());
    }
}
