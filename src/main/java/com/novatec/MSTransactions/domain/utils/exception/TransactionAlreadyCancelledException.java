package com.novatec.MSTransactions.domain.utils.exception;

public class TransactionAlreadyCancelledException extends RuntimeException {
    public TransactionAlreadyCancelledException(String message) {
        super(message);
    }
}
