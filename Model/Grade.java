package com.example.learning_management_system.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Grade {

    @NotEmpty(message = "Id cannot be empty")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Id must consists of letters and numbers only")
    @Size(min = 3, message = "Id must be at least 3 characters")
    private String studentId;

    @NotEmpty(message = "Id cannot be empty")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Id must consists of letters and numbers only")
    @Size(min = 3, message = "Id must be at least 3 characters")
    private String courseId;

    @Min(0)
    @Max(100)
    private double grade;
}
