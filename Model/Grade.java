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

    @Min(value = 0, message = "Grade must be between 0 and 100")
    @Max(value = 100, message = "Grade must be between 0 and 100")
    private Double numericGrade;

    @Pattern(regexp = "[ABCDF]", message = "Grade must be A, B, C, D, and F only")
    private String letterGrade;
}
