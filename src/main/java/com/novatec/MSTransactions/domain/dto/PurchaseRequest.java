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
@ApiModel(value = "purchaseRequest", description = "Estructura de petición para la ejecución de una compra.")
public class PurchaseRequest {

    @ApiModelProperty(notes = "Id de la tarjeta con la que se va a realizar la compra. Longitud: 16 dígitos.")
    @Pattern(regexp = "^\\d{16}$", message = "No cumple con la longitud requerida para el Id de la tarjeta.")
    private String cardId;

    @ApiModelProperty(notes = "Monto de la compra. Valor mínimo: 1 dólar.")
    @Min(value = 1, message = "El monto mínimo de la transacción es de un dólar.")
    private Long price;
}
