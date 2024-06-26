package com.novatec.MSTransactions.web.external;

import com.novatec.MSTransactions.domain.dto.UpdateBalanceRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "card", url = "${card.url}")
public interface ICardService {

    @PostMapping(value = "/balance")
    void updateBalance(@Valid @RequestBody UpdateBalanceRequest updateBalanceRequest);

    @GetMapping(value = "/balance/{cardId}")
    ResponseEntity<String> getBalance(
            @PathVariable("cardId") @Min(value = 1000000000000000L, message = "CardId debe tener 16 dígitos.")
            @Max(value = 9999999999999999L, message = "CardId debe tener 16 dígitos.") Long cardId
    );

    @GetMapping(value = "/{cardId}")
    ResponseEntity<String> getCardDetails(
            @PathVariable("cardId") @Min(value = 1000000000000000L, message = "CardId debe tener 16 dígitos.")
            @Max(value = 9999999999999999L, message = "CardId debe tener 16 dígitos.") Long cardId);
}
