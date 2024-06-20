package com.novatec.MSTransactions.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "cancelTransactionRequest", description = "Estructura de petición para la anulación de una compra.")
public class CancelTransactionRequest {

    @Schema(description = "Id de la tarjeta. Longitud: 16 dígitos.")
    @Pattern(regexp = "^\\d{16}$", message = "No cumple con la longitud requerida para el Id de la tarjeta.")
    private String cardId;

    @Schema(description = "Id de la transacción a anular. Valor mínimo: 1.")
    @Min(value = 1, message = "Id de la transacción es menor al requerido.")
    private Long transactionId;
}
