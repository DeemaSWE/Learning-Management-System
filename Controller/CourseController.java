package com.example.learning_management_system.Controller;

import com.example.learning_management_system.Api.ApiResponse;
import com.example.learning_management_system.Model.Course;
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
    public ResponseEntity getCourses(){
        return ResponseEntity.status(200).body(courseService.getCourses());
    }

    @PostMapping("/add")
    public ResponseEntity addCourse(@RequestBody @Valid Course course, Errors errors){

        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        courseService.addCourse(course);
        return ResponseEntity.status(200).body(new ApiResponse("Course added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCourse(@PathVariable String id, @RequestBody @Valid Course course, Errors errors){

        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        boolean isUpdated = courseService.updateCourse(id, course);

        if(isUpdated)
            return ResponseEntity.status(200).body(new ApiResponse("Course updated"));

        return ResponseEntity.status(400).body(new ApiResponse("No Course found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCourse(@PathVariable String id){

        boolean isDeleted = courseService.deleteCourse(id);

        if(isDeleted)
            return ResponseEntity.status(200).body(new ApiResponse("Course Deleted"));

        return ResponseEntity.status(400).body(new ApiResponse("No Course found"));
    }

    // Calculate the grade needed on a final exam to reach a specified course grade
    @GetMapping("/final-grade-calculator/{desiredGrade}/{currentGrade}/{courseId}")
    public ResponseEntity calculateGradeNeededOnFinal(@PathVariable double desiredGrade, @PathVariable double currentGrade, @PathVariable String courseId) {
        double grade = courseService.calculateGradeNeededOnFinal(desiredGrade, currentGrade, courseId);

        if(grade == -1)
            return ResponseEntity.status(400).body(new ApiResponse("No Course found"));

        return ResponseEntity.status(200).body(new ApiResponse("You will need to score at least "+grade+"% on your final to get a "+desiredGrade+"% overall."));
    }

    // Get a list of courses by credit hours range
    @GetMapping("/credit-hours/{minCreditHours}/{maxCreditHours}")
    public ResponseEntity getCoursesByCreditHoursRange(@PathVariable int minCreditHours, @PathVariable int maxCreditHours) {
        ArrayList<Course> courses = courseService.getCoursesByCreditHoursRange(minCreditHours, maxCreditHours);

        if(courses.isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("No Courses found within the specified credit hour range"));

        return ResponseEntity.status(200).body(courses);
    }

    // Calculate the average credit hours for courses in a department
    @GetMapping("/average-credit-hours/{department}")
    public ResponseEntity calculateAverageCreditHoursForDepartment(@PathVariable String department) {
        double averageCreditHours = courseService.calculateAverageCreditHoursForDepartment(department);

        if (averageCreditHours == -1)
            return ResponseEntity.status(404).body(new ApiResponse("No courses found for the specified department"));

        return ResponseEntity.status(200).body(averageCreditHours);
    }

    // Get a list of courses based on instructor name
    @GetMapping("/search/{instructorName}")
    public ResponseEntity getCoursesByInstructor(@PathVariable String instructorName) {
        ArrayList<Course> courses = courseService.getCoursesByInstructor(instructorName);

        if(courses.isEmpty())
            return ResponseEntity.status(404).body(new ApiResponse("No courses found for the specified instructor"));

        return ResponseEntity.status(200).body(courses);
    }

}

