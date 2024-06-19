package com.novatec.MSTransactions.domain.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancelTransactionRequest {

    @Pattern(regexp = "^\\d{16}$", message = "No cumple con la longitud requerida para el Id de la tarjeta.")
    private Long cardId;

    @Min(value = 1, message = "Id de la transacción es menor al requerido.")
    private Long transactionId;
}
