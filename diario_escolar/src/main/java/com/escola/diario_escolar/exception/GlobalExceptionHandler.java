package com.escola.diario_escolar.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler((ApiException.class))
    public ResponseEntity<ApiErrorResponse> handleNotFound(
        ApiException ex) {

            ApiErrorResponse response = new ApiErrorResponse();
            response.setStatus(ex.getStatus().value());
            response.setError(ex.getMessage());

           return ResponseEntity
            .status(ex.getStatus())
            .body(response);
    }
 
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
          .getFieldErrors()
          .forEach(error ->
              errors.put(error.getField(), error.getDefaultMessage())
          );

        ApiErrorResponse response = new ApiErrorResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setError("Erro de validação");
        response.setFields(errors);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
public ResponseEntity<ApiErrorResponse> handleTypeMismatch(
        MethodArgumentTypeMismatchException ex) {

    ApiErrorResponse response = new ApiErrorResponse();
    response.setStatus(HttpStatus.BAD_REQUEST.value());
    response.setError("Parâmetro inválido: " + ex.getName());

    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(response);
}

 
}