package com.example.learning_management_system.Controller;

import com.example.learning_management_system.Api.ApiResponse;
import com.example.learning_management_system.Model.Student;
import com.example.learning_management_system.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

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

    // Get Top 10 Students with the highest gpa for a specified major
    @GetMapping("/top-10/{major}")
    public ResponseEntity getTopStudents(@PathVariable String major) {
        List<Student> students = studentService.getTopStudents(major);

        if(students.isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("No student found for the specified major"));

        return ResponseEntity.status(200).body(students);
    }

    // Calculates the average gpa for students for a specified major
    @GetMapping("/average-gpa/{major}")
    public ResponseEntity getAverageGpa(@PathVariable String major) {
        double avgGpa = studentService.getAverageGpa(major);

        if(avgGpa == -1)
            return ResponseEntity.status(400).body(new ApiResponse("No students found for the specified major"));

        return ResponseEntity.status(200).body(avgGpa);
    }

    // Get a list of students who have exceeded the expected number of years for their major and are graduating late
    @GetMapping("/late/{major}/{years}")
    public ResponseEntity getLateStudent(@PathVariable String major, @PathVariable int years) {
        ArrayList<Student> students = studentService.getLateStudent(major, years);

        if(students.isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("No student found"));

        return ResponseEntity.status(200).body(students);
    }

    // Get a list of students within the specified age range
    @GetMapping("/get-by-age/{minAge}/{maxAge}")
    public ResponseEntity getStudentsByAgeRange(@PathVariable("minAge") Integer minAge, @PathVariable("maxAge") Integer maxAge) {
        ArrayList<Student> students = studentService.getStudentsByAgeRange(minAge, maxAge);

        if(students.isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("No students found within the specified age range"));

        return ResponseEntity.status(200).body(students);
    }

}
