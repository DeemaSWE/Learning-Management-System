package com.example.learning_management_system.Controller;

import com.example.learning_management_system.Api.ApiResponse;
import com.example.learning_management_system.Model.Grade;
import com.example.learning_management_system.Service.GradeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/grade")
@RequiredArgsConstructor
public class GradeController {

    public final GradeService gradeService;

    @GetMapping("/get")
    public ResponseEntity getGrades(){
        return ResponseEntity.status(200).body(gradeService.getGrades());
    }

    @PostMapping("/add")
    public ResponseEntity addGrade(@RequestBody @Valid Grade grade, Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        gradeService.addGrade(grade);
        return ResponseEntity.status(200).body(new ApiResponse("Grade added"));
    }

    @PutMapping("/update/{studentId}/{courseId}")
    public ResponseEntity updateGrade(@PathVariable String studentId, @PathVariable String courseId, @RequestBody @Valid Grade grade, Errors errors){
        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        boolean isUpdated = gradeService.updateGrade(studentId, courseId, grade);

        if(isUpdated)
            return ResponseEntity.status(200).body(new ApiResponse("Grade updated"));

        return ResponseEntity.status(400).body(new ApiResponse("No grade found for the given studentId and courseId"));
    }

    @DeleteMapping("/delete/{studentId}/{courseId}")
    public ResponseEntity deleteGrade(@PathVariable String studentId, @PathVariable String courseId){
        boolean isDeleted = gradeService.deleteGrade(studentId, courseId);

        if(isDeleted)
            return ResponseEntity.status(200).body(new ApiResponse("Grade Deleted"));

        return ResponseEntity.status(400).body(new ApiResponse("No grade found for the given studentId and courseId"));
    }

    // Get course Grade Distribution
    @GetMapping("course-grade-distribution/{courseId}")
    public ResponseEntity getCourseGradeDistribution(@PathVariable String courseId) {
        String gradeDistribution = gradeService.getCourseGradeDistribution(courseId);

        if (gradeDistribution.isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("No grades found for the specified course id"));

        return ResponseEntity.status(200).body(gradeDistribution);
    }

    // Get student Grade Distribution
    @GetMapping("/student-grade-distribution/{studentId}")
    public ResponseEntity getStudentGradeDistribution(@PathVariable String studentId) {
        String gradeDistribution = gradeService.getStudentGradeDistribution(studentId);

        if (gradeDistribution.isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("No grades found for the specified student id"));

        return ResponseEntity.status(200).body(gradeDistribution);
    }

    // Get the average grade for a specific course
    @GetMapping("/average/{courseId}")
    public ResponseEntity getAverageGradeForCourse(@PathVariable String courseId) {
        double averageGrade = gradeService.getAverageGradeForCourse(courseId);

        if(averageGrade == -1)
            return ResponseEntity.status(400).body(new ApiResponse("No grades found for the specified course id"));

        return ResponseEntity.status(200).body(averageGrade);

    }

    // Get a list of grades for a student's courses based on the specified grade
    @GetMapping("/{studentId}/{grade}")
    public ResponseEntity getCoursesByGrade(@PathVariable String studentId, @PathVariable String grade) {
        ArrayList<String> courseGrade = gradeService.getCoursesByGrade(studentId, grade);

        if (courseGrade.isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("No grades found for the specified student id"));

        return ResponseEntity.status(200).body(courseGrade);
    }

}

