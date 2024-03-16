package com.example.learning_management_system.Controller;

import com.example.learning_management_system.Api.ApiResponse;
import com.example.learning_management_system.Model.Grade;
import com.example.learning_management_system.Service.GradeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

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

}

