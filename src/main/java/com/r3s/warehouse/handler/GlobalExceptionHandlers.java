package com.r3s.warehouse.handler;

import com.r3s.warehouse.exception.BadRequestException;
import com.r3s.warehouse.model.response.ErrorRs;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandlers {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorRs> handleValidationExceptions(MethodArgumentNotValidException ex) {

        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> {
                    FieldError fieldError = (FieldError) error;
                    return fieldError.getField() + ": " + error.getDefaultMessage();
                })
                .collect(Collectors.toList());


        ErrorRs errorRs = new ErrorRs(
                Instant.now(),
                HttpStatus.BAD_REQUEST,
                "Validation Failed",
                errors
        );

        return new ResponseEntity<>(errorRs, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorRs> handleBadRequestException(BadRequestException ex){

        final ErrorRs errorRs = ErrorRs.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .error("Bad Request")
                .build();
        return new ResponseEntity<>(errorRs,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorRs> handleIllegalArgumentException(IllegalArgumentException ex){

        final ErrorRs errorRs = ErrorRs.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .error("Invalid Argument")
                .build();
        return new ResponseEntity<>(errorRs,HttpStatus.BAD_REQUEST);
    }
}
