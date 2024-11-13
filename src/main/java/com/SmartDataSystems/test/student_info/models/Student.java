package com.SmartDataSystems.test.student_info.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_seq")
    @SequenceGenerator(name = "student_id_seq", sequenceName = "student_id_seq", allocationSize = 1)
    Long id;

    @Column(name = "name")
    @NotNull(message = "name cannot be null")
    @NotBlank(message = "name cannot be empty")
    @Pattern(regexp = "^[A-Za-zА-Яа-яЁё\\s'-]+$", message = "name should contain only letters, spaces, apostrophes, or hyphens")
    String name;

    @Column(name = "surname")
    @NotNull(message = "surname cannot be null")
    @NotBlank(message = "surname cannot be empty")
    @Pattern(regexp = "^[A-Za-zА-Яа-яЁё\\s'-]+$",
            message = "surname should contain only letters, spaces, apostrophes, or hyphens")
    String surname;

    @Column(name = "patronymic")
    @Pattern(regexp = "^[A-Za-zА-Яа-яЁё\\s'-]+$",
            message = "patronymic should contain only letters, spaces, apostrophes, or hyphens")
    String patronymic;

    @Column(name = "\"group\"")
    @NotNull(message = "group cannot be null")
    @NotBlank(message = "group cannot be empty")
    @Pattern(regexp = "^[A-Za-zА-Яа-яЁё\\s'-]+$",
            message = "group should contain only letters, spaces, apostrophes, or hyphens")
    String group;

    @Column(name = "average_score")
    Double averageScore;

    @NotNull
    @Column(name = "archived", columnDefinition = "false")
    Boolean archived = false;

    @PrePersist
    public void prePersist() {
        if (archived == null) {
            archived = false;
        }
    }

    @PreUpdate
    public void preUpdate() {
        if (archived == null) {
            archived = false;
        }
    }

}
