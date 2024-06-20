package com.novatec.MSTransactions.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.novatec.MSTransactions.domain.dto.CancelTransactionRequest;
import com.novatec.MSTransactions.domain.dto.PurchaseRequest;
import com.novatec.MSTransactions.domain.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
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
@Tag(name = "transactionController", description = "Controlador que contiene los métodos del microservicio que " +
        "gestiona las transacciones.")
public class TransactionController {

    private final TransactionService transactionService;

    @Operation(summary = "Método que ejecuta la lógica y persistencia de una compra.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Respuesta exitosa, la compra finaliza " +
                            "exitosamente."),
                    @ApiResponse(responseCode = "400", description = "Petición incorrecta."),
                    @ApiResponse(responseCode = "401", description = "Usuario no autorizado."),
                    @ApiResponse(responseCode = "404", description = "No se encuentra el recurso solicitado."),
                    @ApiResponse(responseCode = "500", description = "Error del servidor.")
            }
    )
    @PostMapping("/purchase")
    public ResponseEntity<?> purchase(@Valid @RequestBody PurchaseRequest purchaseRequest)
            throws JsonProcessingException {

        String response = transactionService.purchase(purchaseRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Método que retorna la información de una transacción.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Respuesta exitosa, la información de la " +
                            "transacción se retorna correctamente."),
                    @ApiResponse(responseCode = "400", description = "Petición incorrecta."),
                    @ApiResponse(responseCode = "401", description = "Usuario no autorizado."),
                    @ApiResponse(responseCode = "404", description = "No se encuentra el recurso solicitado."),
                    @ApiResponse(responseCode = "500", description = "Error del servidor.")
            }
    )
    @GetMapping("/{transactionId}")
    public ResponseEntity<?> getTransaction(
            @PathVariable("transactionId")
            @Min(value = 1, message = "El valor de transactionId es menor al permitido.") Long transactionId)
            throws JsonProcessingException {

        String response = transactionService.getTransaction(transactionId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Método que ejecuta la anulación de una transacción.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Respuesta exitosa, la transacción es anulada " +
                            "correctamente."),
                    @ApiResponse(responseCode = "400", description = "Petición incorrecta."),
                    @ApiResponse(responseCode = "401", description = "Usuario no autorizado."),
                    @ApiResponse(responseCode = "404", description = "No se encuentra el recurso solicitado."),
                    @ApiResponse(responseCode = "500", description = "Error del servidor.")
            }
    )
    @PostMapping("/anulation")
    public ResponseEntity<?> cancelTransaction(@Valid @RequestBody CancelTransactionRequest cancelTransactionRequest)
            throws JsonProcessingException {
        String response = transactionService.cancelTransaction(cancelTransactionRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Método que verifica la salud del microservicio.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Respuesta exitosa, el microservicio se " +
                            "ejecuta sin problema."),
                    @ApiResponse(responseCode = "400", description = "Petición incorrecta."),
                    @ApiResponse(responseCode = "401", description = "Usuario no autorizado."),
                    @ApiResponse(responseCode = "404", description = "No se encuentra el recurso solicitado."),
                    @ApiResponse(responseCode = "500", description = "Error del servidor.")
            }
    )
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
