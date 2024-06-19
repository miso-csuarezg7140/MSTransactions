package com.novatec.MSTransactions.persistence.mapper;

import com.novatec.MSTransactions.domain.entity.TransactionDomain;
import com.novatec.MSTransactions.persistence.model.Transaction;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ITransactionMapper {

    @Mappings({
            @Mapping(source = "cardId", target = "cardId"),
            @Mapping(source = "price", target = "price"),
            @Mapping(source = "transactionDate", target = "transactionDate"),
            @Mapping(source = "status", target = "status")
    })
    TransactionDomain toTransactionDomain(Transaction transaction);

    @InheritInverseConfiguration
    Transaction fromTransactionDomain(TransactionDomain transactionDomain);
}
