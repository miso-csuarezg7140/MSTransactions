package com.novatec.MSTransactions.persistence.CRUDRepository;

import com.novatec.MSTransactions.persistence.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface CRUDTransactionRepository extends CrudRepository<Transaction, Long> {
}
