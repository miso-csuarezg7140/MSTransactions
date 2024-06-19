package com.novatec.MSTransactions.persistence.mapper;

import com.novatec.MSTransactions.domain.entity.TransactionDomain;
import com.novatec.MSTransactions.persistence.model.Transaction;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ITransactionMapper {

    TransactionDomain toTransactionDomain(Transaction transaction);

    @InheritInverseConfiguration
    Transaction fromTransactionDomain(Transaction transaction);
}
