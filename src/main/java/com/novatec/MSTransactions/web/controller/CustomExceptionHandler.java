package com.novatec.MSTransactions.web.controller;

import com.novatec.MSTransactions.domain.utils.exception.CardExpiredException;
import com.novatec.MSTransactions.domain.utils.exception.CardNotActivatedException;
import com.novatec.MSTransactions.domain.utils.exception.CardNotFoundException;
import com.novatec.MSTransactions.domain.utils.exception.InsufficientBalanceException;
import com.novatec.MSTransactions.domain.utils.exception.TimeToCancelException;
import com.novatec.MSTransactions.domain.utils.exception.TransactionAlreadyCancelledException;
import com.novatec.MSTransactions.domain.utils.exception.TransactionNotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@Tag(name = "customExceptionHandler", description = "Controlador que contiene el manejo de excepciones del " +
        "microservicio.")
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<String> handleCardNotFound(CardNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(CardNotActivatedException.class)
    public ResponseEntity<String> handleCardNotActivated(CardNotActivatedException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(CardExpiredException.class)
    public ResponseEntity<String> handleCardExpired(CardExpiredException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<String> handleInsufficientBalance(InsufficientBalanceException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(TimeToCancelException.class)
    public ResponseEntity<String> handleTimeToCancel(TimeToCancelException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(TransactionAlreadyCancelledException.class)
    public ResponseEntity<String> handleTransactionAlreadyCancelled(TransactionAlreadyCancelledException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<String> handleTransactionNotFound(TransactionNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<String> handleMethodValid(HandlerMethodValidationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
