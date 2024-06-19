package com.novatec.MSTransactions.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "cancelTransactionRequest", description = "Estructura de petición para la anulación de una compra.")
public class CancelTransactionRequest {

    @ApiModelProperty(notes = "Id de la tarjeta. Longitud: 16 dígitos.")
    @Pattern(regexp = "^\\d{16}$", message = "No cumple con la longitud requerida para el Id de la tarjeta.")
    private String cardId;

    @ApiModelProperty(notes = "Id de la transacción a anular. Valor mínimo: 1.")
    @Min(value = 1, message = "Id de la transacción es menor al requerido.")
    private Long transactionId;
}
