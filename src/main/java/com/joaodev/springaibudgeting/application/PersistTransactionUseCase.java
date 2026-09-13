package com.joaodev.springaibudgeting.application;

import com.joaodev.springaibudgeting.application.input.PersistTransactionInput;
import com.joaodev.springaibudgeting.application.output.TransactionOutput;
import com.joaodev.springaibudgeting.domain.Transaction;
import com.joaodev.springaibudgeting.domain.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class PersistTransactionUseCase {
    private final TransactionRepository transactionRepository;

    public PersistTransactionUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public TransactionOutput execute(PersistTransactionInput input) {
        var transaction = transactionRepository.save(
                new Transaction(input.description(), input.amount(), input.category()));
        return TransactionOutput.from(transaction);
    }
}
