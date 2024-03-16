package com.example.learning_management_system.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Course {

    @NotEmpty(message = "Id cannot be empty")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Id must consists of letters and numbers only")
    @Size(min = 3, message = "Id must be at least 3 characters")
    private String id;

    @NotEmpty(message = "Title cannot be empty")
    @Size(min = 3, max = 20, message = "Title must be between 3 and 20 characters")
    private String title;

    @NotEmpty(message = "Description cannot be empty")
    @Size(min = 10, max = 100, message = "Description must be between 10 and 100 characters")
    private String description;

    private int hours;

    private String instructor;

    @Min(1)
    @Max(100)
    private int finalExamWeight;

}
