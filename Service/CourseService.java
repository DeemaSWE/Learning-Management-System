package com.example.learning_management_system.Service;

import com.example.learning_management_system.Model.Course;
import com.example.learning_management_system.Model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CourseService {
    ArrayList<Course> courses = new ArrayList<>();
    private final StudentService studentService;

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course student) {
        courses.add(student);
    }

    public boolean updateCourse(String id, Course updatedCourse) {
        Course course = getCourseById(id);
        if (course == null)
            return false;

        courses.set(courses.indexOf(course), updatedCourse);
        return true;
    }

    public boolean deleteCourse(String id) {
        Course course = getCourseById(id);
        if (course == null)
            return false;

        courses.remove(course);
        return true;

    }

    // Calculate the grade needed on a final exam to reach a specified course grade
    public double calculateGradeNeededOnFinal(double desiredGrade, double currentGrade, String courseId) {
        Course course = getCourseById(courseId);

        if (course == null)
            return -1;

        int finalExamWeight = course.getFinalExamWeight() / 100; // Convert percentage to decimal

        double requiredFinalExamGrade = (desiredGrade - (currentGrade * (1 - finalExamWeight))) / course.getFinalExamWeight();

        return requiredFinalExamGrade;
    }

    // Search for course based on instructor name (assuming a field for instructor name exists in Course model)
    public ArrayList<Course> getCoursesByInstructor(String instructorName) {
        ArrayList<Course> CoursesByInstructor = new ArrayList<>();

        for (Course course : courses) {
            if (course.getInstructor().equals(instructorName)) {
                CoursesByInstructor.add(course);
            }
        }
        return CoursesByInstructor;
    }

    public Course getCourseById(String courseId) {
        for (Course course : courses) {
            if (course.getId().equals(courseId)) {
                return course;
            }
        }
        return null;
    }


}
