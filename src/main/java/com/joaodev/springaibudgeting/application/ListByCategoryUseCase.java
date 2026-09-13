package com.joaodev.springaibudgeting.application;

import com.joaodev.springaibudgeting.application.output.TransactionOutput;
import com.joaodev.springaibudgeting.domain.Category;
import com.joaodev.springaibudgeting.domain.TransactionRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListByCategoryUseCase {
    private final TransactionRepository transactionRepository;

    public ListByCategoryUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Tool(name = "list-transactions-by-category", description = "Lista transações financeiras por categoria")
    public List<TransactionOutput> execute(@ToolParam(description = "Categoria de uma transação") Category category) {
        return transactionRepository.findAllByCategory(category)
                .stream()
                .map(TransactionOutput::from)
                .toList();
    }
}
