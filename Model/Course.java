package com.example.learning_management_system.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Course {

    @NotEmpty(message = "Id cannot be empty")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Id must consists of letters and numbers only")
    @Size(min = 3, message = "Id must be at least 3 characters")
    private String id;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
    private String name;

    @NotEmpty(message = "Description cannot be empty")
    @Size(min = 10, max = 100, message = "Description must be between 10 and 100 characters")
    private String description;

    @Positive(message = "Credit hours mus be a positive integer")
    private Integer creditHours;

    @NotEmpty(message = "Department cannot be empty")
    private String department;

    @NotEmpty(message = "Instructor cannot be empty")
    private String instructor;

    @Min(value = 1, message = "Final exam weight must be between 1 and 100")
    @Max(value = 100, message = "Final exam weight must be between 1 and 100")
    private Integer finalExamWeight;

}
