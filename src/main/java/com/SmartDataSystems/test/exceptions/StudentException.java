package com.SmartDataSystems.test.exceptions;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public abstract class StudentException extends RuntimeException{
    HttpStatus status;
    Object payload;

    public StudentException(Object payload, HttpStatus status) {
        super(payload.toString());
        this.payload = payload;
        this.status = status;
    }
}
