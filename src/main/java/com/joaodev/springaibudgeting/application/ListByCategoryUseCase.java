package com.joaodev.springaibudgeting.application;

import com.joaodev.springaibudgeting.application.output.TransactionOutput;
import com.joaodev.springaibudgeting.domain.Category;
import com.joaodev.springaibudgeting.domain.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListByCategoryUseCase {
    private final TransactionRepository transactionRepository;

    public ListByCategoryUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<TransactionOutput> execute(Category category) {
        return transactionRepository.findAllByCategory(category)
                .stream()
                .map(TransactionOutput::from)
                .toList();
    }
}
