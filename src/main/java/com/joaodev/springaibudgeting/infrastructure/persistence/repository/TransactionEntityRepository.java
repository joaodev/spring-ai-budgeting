package com.joaodev.springaibudgeting.infrastructure.persistence.repository;

import com.joaodev.springaibudgeting.domain.Category;
import com.joaodev.springaibudgeting.infrastructure.persistence.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionEntityRepository extends CrudRepository<TransactionEntity, UUID> {
    List<TransactionEntity> findAllByCategory(Category category);
}
