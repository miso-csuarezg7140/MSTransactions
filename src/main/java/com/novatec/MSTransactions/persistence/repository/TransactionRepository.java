package com.novatec.MSTransactions.persistence.repository;

import com.novatec.MSTransactions.domain.entity.TransactionDomain;
import com.novatec.MSTransactions.domain.repository.ITransactionRepository;
import com.novatec.MSTransactions.persistence.CRUDRepository.CRUDTransactionRepository;
import com.novatec.MSTransactions.persistence.mapper.ITransactionMapper;
import com.novatec.MSTransactions.persistence.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TransactionRepository implements ITransactionRepository {

    private final CRUDTransactionRepository crudTransactionRepository;

    private final ITransactionMapper transactionMapper;

    @Override
    public TransactionDomain save(TransactionDomain transactionDomain) {
        Transaction transaction = crudTransactionRepository.save(transactionMapper
                .fromTransactionDomain(transactionDomain));
        return transactionMapper.toTransactionDomain(transaction);
    }

    @Override
    public TransactionDomain findById(Long transactionId) {
        return transactionMapper.toTransactionDomain(crudTransactionRepository.findById(transactionId)
                .orElse(null));
    }
}
