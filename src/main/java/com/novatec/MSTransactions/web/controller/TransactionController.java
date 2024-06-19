package com.novatec.MSTransactions.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.novatec.MSTransactions.domain.dto.CancelTransactionRequest;
import com.novatec.MSTransactions.domain.dto.PurchaseRequest;
import com.novatec.MSTransactions.domain.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(value = "Controlador que contiene los métodos del microservicio que gestiona las transacciones.")
public class TransactionController {

    private final TransactionService transactionService;

    @ApiOperation(value = "Método que ejecuta la lógica y persistencia de una compra.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Respuesta exitosa, la compra finaliza exitosamente."),
                    @ApiResponse(code = 400, message = "Petición incorrecta."),
                    @ApiResponse(code = 401, message = "Usuario no autorizado."),
                    @ApiResponse(code = 404, message = "No se encuentra el recurso solicitado."),
                    @ApiResponse(code = 500, message = "Error del servidor.")
            }
    )
    @PostMapping("/purchase")
    public ResponseEntity<?> purchase(@Valid @RequestBody PurchaseRequest purchaseRequest)
            throws JsonProcessingException {

        String response = transactionService.purchase(purchaseRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Método que retorna la información de una transacción.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Respuesta exitosa, la información de la transacción se " +
                            "retorna correctamente."),
                    @ApiResponse(code = 400, message = "Petición incorrecta."),
                    @ApiResponse(code = 401, message = "Usuario no autorizado."),
                    @ApiResponse(code = 404, message = "No se encuentra el recurso solicitado."),
                    @ApiResponse(code = 500, message = "Error del servidor.")
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

    @ApiOperation(value = "Método que ejecuta la anulación de una transacción.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Respuesta exitosa, la transacción es anulada correctamente."),
                    @ApiResponse(code = 400, message = "Petición incorrecta."),
                    @ApiResponse(code = 401, message = "Usuario no autorizado."),
                    @ApiResponse(code = 404, message = "No se encuentra el recurso solicitado."),
                    @ApiResponse(code = 500, message = "Error del servidor.")
            }
    )
    @PostMapping("/anulation")
    public ResponseEntity<?> cancelTransaction(@Valid @RequestBody CancelTransactionRequest cancelTransactionRequest)
            throws JsonProcessingException {
        String response = transactionService.cancelTransaction(cancelTransactionRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ApiOperation(value = "Método que verifica la salud del microservicio.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Respuesta exitosa, el microservicio se ejecuta sin problema."),
                    @ApiResponse(code = 400, message = "Petición incorrecta."),
                    @ApiResponse(code = 401, message = "Usuario no autorizado."),
                    @ApiResponse(code = 404, message = "No se encuentra el recurso solicitado."),
                    @ApiResponse(code = 500, message = "Error del servidor.")
            }
    )
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
