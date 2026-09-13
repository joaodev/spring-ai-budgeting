package com.joaodev.springaibudgeting.infrastructure.http.request;

import com.joaodev.springaibudgeting.application.input.PersistTransactionInput;
import com.joaodev.springaibudgeting.domain.Category;

public record TransactionRequest(String description, Category category, double amount) {
    public PersistTransactionInput toInput() {
        return new PersistTransactionInput(description, amount, category);
    }
}
