package com.novatec.MSTransactions.domain.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TransactionState {

    INVALID(false),
    VALID(true);

    private Boolean status;
}
