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

        double finalExamWeight = course.getFinalExamWeight() / 100.0;
        double currentWeight = 1 - finalExamWeight;
        double requiredFinalExamGrade = (desiredGrade - (currentWeight * currentGrade)) / finalExamWeight;

        return requiredFinalExamGrade;
    }

    // Get a list of courses by credit hours range
    public ArrayList<Course> getCoursesByCreditHoursRange(int minCreditHours, int maxCreditHours) {
        ArrayList<Course> list = new ArrayList<>();

        for (Course course : courses) {
            if (course.getCreditHours() >= minCreditHours && course.getCreditHours() <= maxCreditHours)
                list.add(course);
        }

        return list;
    }

    // Calculate the average credit hours for courses in a department
    public double calculateAverageCreditHoursForDepartment(String department) {
        double creditHoursSum = 0.0;
        int courseCount = 0;

        for (Course course : courses) {
            if (course.getDepartment().equalsIgnoreCase(department)) {
                creditHoursSum += course.getCreditHours();
                courseCount++;
            }
        }

        if (courseCount == 0)
            return -1;

        return creditHoursSum / courseCount;
    }

    // Get a list of courses based on instructor name
    public ArrayList<Course> getCoursesByInstructor(String instructorName) {
        ArrayList<Course> list = new ArrayList<>();

        for (Course course : courses) {
            if (course.getInstructor().equals(instructorName)) {
                list.add(course);
            }
        }
        return list;
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
