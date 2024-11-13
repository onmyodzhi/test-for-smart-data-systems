package com.SmartDataSystems.test.student_info.models.DTOs;

import lombok.Value;

/**
 * DTO for {@link com.SmartDataSystems.test.student_info.models.Student}
 */
@Value
public class StudentDto {

    Long id;

    String name;

    String surname;

    String patronymic;

    String group;

    Double averageScore;

    Boolean archived;
}