package com.novatec.MSTransactions.persistence.CRUDRepository;

import com.novatec.MSTransactions.persistence.model.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CRUDTransactionRepository extends CrudRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.transactionId = :transactionId AND t.cardId = :cardId")
    Transaction findByIdAndCardId (@Param("transactionId") Long transactionId, @Param("cardId") Long cardId);
}
