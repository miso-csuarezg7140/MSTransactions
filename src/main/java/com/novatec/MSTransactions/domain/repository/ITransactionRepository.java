package com.novatec.MSTransactions.domain.repository;

import com.novatec.MSTransactions.domain.entity.TransactionDomain;

public interface ITransactionRepository {

    TransactionDomain save(TransactionDomain transactionDomain);

    TransactionDomain findById(Long transactionId);

    TransactionDomain findByIdAndCardId(Long transactionId, Long cardId);
}
