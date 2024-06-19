package com.novatec.MSTransactions.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDomain {

    private Long transactionId;
    private Long cardId;
    private Long price;
    private LocalDateTime transactionDate;
    private Boolean status;
}
