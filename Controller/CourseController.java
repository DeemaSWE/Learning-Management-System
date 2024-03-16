package com.example.learning_management_system.Controller;

import com.example.learning_management_system.Api.ApiResponse;
import com.example.learning_management_system.Model.Course;
import com.example.learning_management_system.Model.Student;
import com.example.learning_management_system.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/get")
    public ResponseEntity getStudents(){
        return ResponseEntity.status(200).body(courseService.getCourses());
    }

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody @Valid Course course, Errors errors){

        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        courseService.addCourse(course);
        return ResponseEntity.status(200).body(new ApiResponse("Course added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@PathVariable String id, @RequestBody @Valid Course course, Errors errors){

        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        boolean isUpdated = courseService.updateCourse(id, course);

        if(isUpdated)
            return ResponseEntity.status(200).body(new ApiResponse("Course updated"));

        return ResponseEntity.status(400).body(new ApiResponse("No Course found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable String id){

        boolean isDeleted = courseService.deleteCourse(id);

        if(isDeleted)
            return ResponseEntity.status(200).body(new ApiResponse("Course Deleted"));

        return ResponseEntity.status(400).body(new ApiResponse("No Course found"));
    }

    // Calculate the grade needed on a final exam to reach a specified course grade
    @GetMapping("/final-grade-calculator/{desiredGrade}/{currentGrade}/{courseId}")
    public ResponseEntity calculateGradeNeededOnFinal(@PathVariable double desiredGrade, @PathVariable double currentGrade, @PathVariable String courseId) {
        double grade = courseService.calculateGradeNeededOnFinal(desiredGrade, currentGrade, courseId);
        return ResponseEntity.status(200).body(grade);
    }

    // Search for course based on instructor name
    @GetMapping("/get/{instructorName}")
    public ResponseEntity getCoursesByInstructor(@PathVariable String instructorName) {
        ArrayList<Course> courses = courseService.getCoursesByInstructor(instructorName);
        return ResponseEntity.status(200).body(courses);
    }

}

