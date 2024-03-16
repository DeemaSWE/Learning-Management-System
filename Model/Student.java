package com.example.learning_management_system.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

@Data
@AllArgsConstructor
public class Student {

    @NotEmpty(message = "Id cannot be empty")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Id must consists of letters and numbers only")
    @Size(min = 3, message = "Id must be at least 3 characters")
    private String id;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 4, max = 10, message = "Name must be between 4 and 10 characters")
    private String name;

    @Email(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
            message = "Invalid email format")
    private String email;

    @Pattern(regexp = "^05\\d{8}$", message = "Phone number must start with 05 and consist of exactly 10 digits")
    private String phoneNumber;

    @NotNull(message = "Age cannot be empty")
    @Min(value = 18, message = "Age must be at least 18")
    private Integer age;

    @NotNull(message = "GPA cannot be empty")
    @Min(value = 0, message = "GPA must be between 0 and 5")
    @Max(value = 5, message = "GPA must be between 0 and 5")
    private Integer gpa;

    @NotEmpty(message = "Major cannot be empty")
    @Size(min = 3, max = 20, message = "Major must be between 3 and 20 characters")
    private String major;

    private LocalDate enrollmentDate;

    private boolean isGraduated;

    @Positive
    private int currentLevel;

}
