package com.SmartDataSystems.test.exceptions.handlers;

import com.SmartDataSystems.test.exceptions.StudentException;
import com.SmartDataSystems.test.exceptions.responses.MessageResponse;
import com.fasterxml.jackson.core.JsonParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class StudentExceptionHandler {
    public ResponseEntity<MessageResponse> handleJsonParseException(JsonParseException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(ex.getMessage()));
    }

    @ExceptionHandler(StudentException.class)
    public ResponseEntity<?> handleWalletException(StudentException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(ex.getPayload());
    }
}
