package com.joaodev.springaibudgeting.infrastructure.http;

import com.joaodev.springaibudgeting.application.ListByCategoryUseCase;
import com.joaodev.springaibudgeting.application.PersistTransactionUseCase;
import com.joaodev.springaibudgeting.domain.Category;
import com.joaodev.springaibudgeting.infrastructure.http.request.TransactionRequest;
import com.joaodev.springaibudgeting.infrastructure.http.response.TransactionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final PersistTransactionUseCase persistTransactionUseCase;
    private final ListByCategoryUseCase listByCategoryUseCase;

    public TransactionController(PersistTransactionUseCase persistTransactionUseCase,
                                 ListByCategoryUseCase listByCategoryUseCase) {
        this.persistTransactionUseCase = persistTransactionUseCase;
        this.listByCategoryUseCase = listByCategoryUseCase;
    }

    @GetMapping("/{category}")
    public List<TransactionResponse> listByCategory(@PathVariable Category category) {
        return listByCategoryUseCase.execute(category)
                .stream()
                .map(TransactionResponse::from)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponse createTransaction(@RequestBody TransactionRequest request) {
        var transaction = persistTransactionUseCase.execute(request.toInput());
        return TransactionResponse.from(transaction);
    }
}
