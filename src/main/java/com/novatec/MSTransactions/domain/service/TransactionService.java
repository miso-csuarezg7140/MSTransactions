package com.novatec.MSTransactions.domain.service;

import com.novatec.MSTransactions.domain.dto.CancelTransactionRequest;
import com.novatec.MSTransactions.domain.dto.PurchaseRequest;
import com.novatec.MSTransactions.domain.repository.ITransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final ITransactionRepository transactionRepository;

    public String purchase(PurchaseRequest purchaseRequest) {
        return null;
    }

    public String getTransaction(Long transactionId) {
        return null;
    }

    public String cancelTransaction(CancelTransactionRequest cancelTransactionRequest) {
        return null;
    }
}
