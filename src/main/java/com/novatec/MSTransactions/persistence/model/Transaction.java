package com.novatec.MSTransactions.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "transaction", schema= "public")
public class Transaction {

    @Id
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "card_id")
    private Long cardId;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @Column(name = "status")
    private Boolean status;
}
