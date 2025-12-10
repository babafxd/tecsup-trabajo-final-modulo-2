package com.tecsup.banco.digital.arquitectura.ms.app.infraestructure.exception;

import com.tecsup.banco.digital.arquitectura.ms.app.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 400 - Validaciones en @RequestBody (Bean Validation)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex, WebRequest request) {
        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining("; "));

        ErrorResponse body = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "VALIDATION_ERROR",
                message,
                request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    // 400 - JSON mal formado
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, WebRequest request) {
        ErrorResponse body = new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
                "MALFORMED_JSON", "El cuerpo de la petición está mal formado o faltan campos.", request.getDescription(false).replace("uri=", ""));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    // 400 - Excepciones de dominio
    @ExceptionHandler(InvalidClientDataException.class)
    protected ResponseEntity<ErrorResponse> handleInvalidClientData(InvalidClientDataException ex, WebRequest request) {
        ErrorResponse body = new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
                "INVALID_CLIENT_DATA", ex.getMessage(), request.getDescription(false).replace("uri=", ""));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(InvalidAccountDataException.class)
    protected ResponseEntity<ErrorResponse> handleInvalidClientData(InvalidAccountDataException ex, WebRequest request) {
        ErrorResponse body = new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
                "INVALID_CLIENT_DATA", ex.getMessage(), request.getDescription(false).replace("uri=", ""));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleInvalidClientData(AccountNotFoundException ex, WebRequest request) {
        ErrorResponse body = new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
                "INVALID_CLIENT_DATA", ex.getMessage(), request.getDescription(false).replace("uri=", ""));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(InvalidTransactionDataException.class)
    protected ResponseEntity<ErrorResponse> handleInvalidClientData(InvalidTransactionDataException ex, WebRequest request) {
        ErrorResponse body = new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
                "INVALID_CLIENT_DATA", ex.getMessage(), request.getDescription(false).replace("uri=", ""));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }



    // 500 - Error general
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleAll(Exception ex, WebRequest request) {
        // Log completo aquí (ej. logger.error(..., ex))
        ErrorResponse body = new ErrorResponse(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL_SERVER_ERROR", "Ocurrió un error inesperado. Intente nuevamente.", request.getDescription(false).replace("uri=", ""));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    @ExceptionHandler(InvalidTransactionException.class)
    protected ResponseEntity<ErrorResponse> handleInvalidClientData(InvalidTransactionException ex, WebRequest request) {
        ErrorResponse body = new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
                "INTERNAL_SERVER_ERROR", ex.getMessage(), request.getDescription(false).replace("uri=", ""));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}
