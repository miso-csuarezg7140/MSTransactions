package com.novatec.MSTransactions.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.novatec.MSTransactions.domain.dto.CancelTransactionRequest;
import com.novatec.MSTransactions.domain.dto.PurchaseRequest;
import com.novatec.MSTransactions.domain.service.TransactionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/purchase")
    public ResponseEntity<?> purchase(@Valid @RequestBody PurchaseRequest purchaseRequest)
            throws JsonProcessingException {

        String response = transactionService.purchase(purchaseRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<?> getTransaction(
            @PathVariable("transactionId")
            @Min(value = 1, message = "El valor de transactionId es menor al permitido.") Long transactionId)
            throws JsonProcessingException {

        String response = transactionService.getTransaction(transactionId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/anulation")
    public ResponseEntity<?> cancelTransaction(@Valid @RequestBody CancelTransactionRequest cancelTransactionRequest)
            throws JsonProcessingException {
        String response = transactionService.cancelTransaction(cancelTransactionRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
