package com.joaodev.springaibudgeting.domain;

public record Transaction(TransactionId id, String description, double amount, Category category) {
    public Transaction(String description, double amount, Category category) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("descrição não pode ser vazia");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("valor deve ser positivo");
        }
        if (category == null) {
            throw new IllegalArgumentException("categoria é obrigatória");
        }

        this(new TransactionId(), description, amount, category);
    }
}
