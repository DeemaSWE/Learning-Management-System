package com.example.learning_management_system.Controller;

import com.example.learning_management_system.Api.ApiResponse;
import com.example.learning_management_system.Model.Student;
import com.example.learning_management_system.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity getStudents(){
        return ResponseEntity.status(200).body(studentService.getStudents());
    }

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody @Valid Student student, Errors errors){

        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        studentService.addStudent(student);
        return ResponseEntity.status(200).body(new ApiResponse("Student added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@PathVariable String id, @RequestBody @Valid Student student, Errors errors){

        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        boolean isUpdated = studentService.updateStudent(id, student);

        if(isUpdated)
            return ResponseEntity.status(200).body(new ApiResponse("Student updated"));

        return ResponseEntity.status(400).body(new ApiResponse("No student found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable String id){

        boolean isDeleted = studentService.deleteStudent(id);

        if(isDeleted)
            return ResponseEntity.status(200).body(new ApiResponse("Student Deleted"));

        return ResponseEntity.status(400).body(new ApiResponse("No student found"));
    }

    // Get 10 top performing students for a specified major based on the gpa
    @GetMapping("/top10/{major}")
    public ResponseEntity getTopStudents(@PathVariable String major) {

        List<Student> students = studentService.getTopStudents(major);
        return ResponseEntity.status(200).body(students);
    }

    // Get the average gpa for a specified major
    @GetMapping("/students/average-gpa/{major}")
    public ResponseEntity getAverageGpa(@PathVariable String major) {

        double avgGpa = studentService.getAverageGpa(major);
        return ResponseEntity.status(200).body(avgGpa);
    }

    // Get a list of students that will graduate this semester
    @GetMapping("/graduating/{major}/{majorDuration}")
    public ResponseEntity getGraduatingStudents(@PathVariable String major) {

        ArrayList<Student> students = studentService.getGraduatingStudents(major);
        return ResponseEntity.status(200).body(students);
    }

}
