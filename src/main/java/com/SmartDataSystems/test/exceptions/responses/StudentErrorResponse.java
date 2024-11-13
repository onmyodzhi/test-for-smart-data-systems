package com.SmartDataSystems.test.exceptions.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentErrorResponse {
    String message;
    Long id;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("StudentErrorResponse [message=");
        builder.append(message);

        if (id != null) {
            builder.append(", id=");
            builder.append(id);
        }

        builder.append("]");
        return builder.toString();
    }
}
