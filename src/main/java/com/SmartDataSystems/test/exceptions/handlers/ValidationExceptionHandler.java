package com.SmartDataSystems.test.exceptions.handlers;

import com.SmartDataSystems.test.exceptions.responses.ValidationError;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@Slf4j
@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<?> processConstraintViolationException(ConstraintViolationException processedException) {
        Map<String, List<String>> errorsDescriptions = new HashMap<>();
        List<ConstraintViolation<?>> unprocessedViolations = new ArrayList<>();

        for (var constraintViolation : processedException.getConstraintViolations()) {
            String field = constraintViolation.getPropertyPath().toString();
            String message = constraintViolation.getMessage();
            errorsDescriptions.computeIfAbsent(field, k -> new ArrayList<>()).add(message);
        }

        if (!unprocessedViolations.isEmpty()) {
            log.error("Unprocessed constraint violation detected: {}", unprocessedViolations);
        }

        return ResponseEntity.badRequest().body(new ValidationError(HttpStatus.BAD_REQUEST.value(), "Validation error", errorsDescriptions));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> processMethodArgumentNotValidException(MethodArgumentNotValidException processedException) {
        BindingResult bindingResult = processedException.getBindingResult();
        Map<String, List<String>> fieldErrors = new HashMap<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String field = fieldError.getField();
            String errorMessage = fieldError.getDefaultMessage();
            fieldErrors.computeIfAbsent(field, key -> new ArrayList<>()).add(errorMessage);
        }

        fieldErrors.forEach((field, messages) -> {
            List<String> uniqueMessages = new ArrayList<>(new HashSet<>(messages));
            fieldErrors.put(field, uniqueMessages);
        });

        fieldErrors.forEach((field, messages) -> messages.forEach(message ->
                log.error("Field: {}, Error: {}", field, message)
        ));

        return new ResponseEntity<>(new ValidationError(HttpStatus.BAD_REQUEST.value(),
                "Validation error", fieldErrors), HttpStatus.BAD_REQUEST);
    }
}

