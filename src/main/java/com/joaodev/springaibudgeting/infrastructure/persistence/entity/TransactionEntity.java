package com.joaodev.springaibudgeting.infrastructure.persistence.entity;

import com.joaodev.springaibudgeting.domain.Category;
import com.joaodev.springaibudgeting.domain.Transaction;
import com.joaodev.springaibudgeting.domain.TransactionId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity {
    @Id
    private UUID id;
    private String description;
    private double amount;

    @Enumerated(EnumType.STRING)
    private Category category;

    public static TransactionEntity from(Transaction transaction) {
        return new TransactionEntity(
                transaction.id().uuid(),
                transaction.description(),
                transaction.amount(),
                transaction.category()
        );
    }

    public Transaction toDomain() {
        return new Transaction(
                new TransactionId(this.id),
                this.description,
                this.amount,
                this.category
        );
    }
}
