package com.novatec.MSTransactions.domain.utils.exception;

public class CardNotActivatedException extends RuntimeException {
    public CardNotActivatedException(String message) {
        super(message);
    }
}
