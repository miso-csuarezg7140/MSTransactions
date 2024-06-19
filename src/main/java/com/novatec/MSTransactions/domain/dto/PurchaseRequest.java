package com.novatec.MSTransactions.domain.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRequest {

    @Pattern(regexp = "^\\d{16}$", message = "No cumple con la longitud requerida para el Id de la tarjeta.")
    private String cardId;

    @Min(value = 1, message = "El monto mínimo de la transacción es de un dólar.")
    private Long price;
}
