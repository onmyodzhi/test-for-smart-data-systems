package com.SmartDataSystems.test.exceptions;

import com.SmartDataSystems.test.exceptions.responses.StudentNotFoundResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentNotFoundException extends StudentException {
    public StudentNotFoundException(Long id) {
        super(new StudentNotFoundResponse("Student with id: " + id + " not found", id), HttpStatus.NOT_FOUND);
    }
}
