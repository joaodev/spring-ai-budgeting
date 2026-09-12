package com.joaodev.springaibudgeting.application.input;

import com.joaodev.springaibudgeting.domain.Category;

public record PersistTransactionInput(String description, double amount, Category category) {
}
