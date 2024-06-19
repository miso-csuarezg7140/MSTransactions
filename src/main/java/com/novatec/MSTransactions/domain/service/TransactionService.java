package com.novatec.MSTransactions.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.novatec.MSTransactions.domain.dto.BalanceResponse;
import com.novatec.MSTransactions.domain.dto.CancelTransactionRequest;
import com.novatec.MSTransactions.domain.dto.CardDetailResponse;
import com.novatec.MSTransactions.domain.dto.PurchaseRequest;
import com.novatec.MSTransactions.domain.dto.UpdateBalanceRequest;
import com.novatec.MSTransactions.domain.entity.TransactionDomain;
import com.novatec.MSTransactions.domain.repository.ITransactionRepository;
import com.novatec.MSTransactions.domain.utils.CardState;
import com.novatec.MSTransactions.domain.utils.TransactionState;
import com.novatec.MSTransactions.domain.utils.exception.CardExpiredException;
import com.novatec.MSTransactions.domain.utils.exception.CardNotActivatedException;
import com.novatec.MSTransactions.domain.utils.exception.CardNotFoundException;
import com.novatec.MSTransactions.domain.utils.exception.InsufficientBalanceException;
import com.novatec.MSTransactions.domain.utils.exception.TimeToCancelException;
import com.novatec.MSTransactions.domain.utils.exception.TransactionAlreadyCancelledException;
import com.novatec.MSTransactions.domain.utils.exception.TransactionNotFoundException;
import com.novatec.MSTransactions.web.external.ICardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final ITransactionRepository transactionRepository;

    private final ICardService cardService;

    private final ObjectMapper objectMapper;

    public String purchase(PurchaseRequest purchaseRequest) throws JsonProcessingException {

        Long cardNumber = Long.parseLong(purchaseRequest.getCardId());
        LocalDateTime today = LocalDateTime.now();
        String cardDetailResponseStr = cardService.getCardDetails(cardNumber).getBody();
        CardDetailResponse cardDetailResponse = objectMapper.readValue(cardDetailResponseStr, CardDetailResponse.class);

        if (cardDetailResponse != null) {
            if (Objects.equals(CardState.ACTIVE.getCardState(), cardDetailResponse.getStatus())) {
                if (cardDetailResponse.getDueDate().isAfter(today)) {

                    String balanceResponseStr = cardService.getBalance(cardNumber).getBody();
                    BalanceResponse balanceResponse = objectMapper.readValue(balanceResponseStr, BalanceResponse.class);

                    assert balanceResponse != null;
                    if (balanceResponse.getBalance() >= purchaseRequest.getPrice()) {

                        TransactionDomain transactionDomain = new TransactionDomain();
                        transactionDomain.setPrice(purchaseRequest.getPrice());
                        transactionDomain.setTransactionDate(today);
                        transactionDomain.setStatus(TransactionState.VALID.getStatus());
                        transactionDomain.setCardId(cardNumber);
                        transactionDomain = transactionRepository.save(transactionDomain);

                        UpdateBalanceRequest updateBalanceRequest = new UpdateBalanceRequest();
                        updateBalanceRequest.setBalance(-1 * purchaseRequest.getPrice());
                        updateBalanceRequest.setCardId(String.valueOf(purchaseRequest.getCardId()));
                        cardService.updateBalance(updateBalanceRequest);

                        return objectMapper.writeValueAsString(transactionDomain);
                    } else {
                        throw new InsufficientBalanceException("Card with ID " + purchaseRequest.getCardId() +
                                " doesn't have enough balance for the transaction.");
                    }
                } else {
                    throw new CardExpiredException("Card with ID " + purchaseRequest.getCardId() +
                            " is expired.");
                }
            } else {
                throw new CardNotActivatedException("Card with ID " + purchaseRequest.getCardId() +
                        " is not activated");
            }
        } else {
            throw new CardNotFoundException("Card with ID " + purchaseRequest.getCardId() + " doesn't exist.");
        }
    }

    /**
     * <p></p>
     *
     * @param transactionId
     * @return
     * @throws JsonProcessingException
     */
    public String getTransaction(Long transactionId) throws JsonProcessingException {

        TransactionDomain transactionDomain = transactionRepository.findById(transactionId);

        if (transactionDomain != null) {
            return objectMapper.writeValueAsString(transactionDomain);
        } else {
            throw new TransactionNotFoundException("Transaction with ID " + transactionId + " doesn't exist.");
        }
    }

    /**
     * <p></p>
     *
     * @param cancelTransactionRequest
     * @return
     * @throws JsonProcessingException
     */
    public String cancelTransaction(CancelTransactionRequest cancelTransactionRequest) throws JsonProcessingException {

        LocalDateTime today = LocalDateTime.now();
        TransactionDomain transactionDomain = transactionRepository
                .findById(cancelTransactionRequest.getTransactionId());

        if (transactionDomain != null) {
            if (TransactionState.VALID.getStatus() == transactionDomain.getStatus()) {

                Duration differenceTime = Duration.between(transactionDomain.getTransactionDate(), today);

                if (24 >= differenceTime.toHours()) {

                    transactionDomain.setStatus(TransactionState.INVALID.getStatus());
                    transactionDomain = transactionRepository.save(transactionDomain);

                    UpdateBalanceRequest updateBalanceRequest = new UpdateBalanceRequest();
                    updateBalanceRequest.setCardId(String.valueOf(transactionDomain.getCardId()));
                    updateBalanceRequest.setBalance(transactionDomain.getPrice());
                    cardService.updateBalance(updateBalanceRequest);

                    return objectMapper.writeValueAsString(transactionDomain);

                } else {
                    throw new TimeToCancelException("Transaction with ID "
                            + cancelTransactionRequest.getTransactionId() + " doesn't allow the cancel due to time.");
                }
            } else {
                throw new TransactionAlreadyCancelledException("Transaction with ID " +
                        cancelTransactionRequest.getTransactionId() + " is already canceled.");
            }
        } else {
            throw new TransactionNotFoundException("Transaction with ID " +
                    cancelTransactionRequest.getTransactionId() + " doesn't exist.");
        }
    }
}
