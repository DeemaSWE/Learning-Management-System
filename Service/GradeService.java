package com.example.learning_management_system.Service;

import com.example.learning_management_system.Model.Course;
import com.example.learning_management_system.Model.Grade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class GradeService {

    ArrayList<Grade> grades = new ArrayList<>();
    private final CourseService courseService;

    public ArrayList<Grade> getGrades() {
        return grades;
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    public boolean updateGrade(String studentId, String courseId, Grade updatedGrade){
        Grade grade = getGradeByStudentAndCourseId(studentId, courseId);

        if(grade == null)
            return false;

        grades.set(grades.indexOf(grade), updatedGrade);
        return true;
    }

    public boolean deleteGrade(String studentId, String courseId) {
        Grade grade = getGradeByStudentAndCourseId(studentId, courseId);

        if (grade == null)
            return false;

        grades.remove(grade);
        return true;
    }

    // Get Course Grade Distribution
    public String getCourseGradeDistribution(String courseId) {
        int countA = 0;
        int countB = 0;
        int countC = 0;
        int countD = 0;
        int countF = 0;

        ArrayList<Grade> list = getGradesByCourseId(courseId);

        for (Grade grade : list) {
            if (grade.getLetterGrade().equals("A")) {
                countA++ ;
            } else if (grade.getLetterGrade().equals("B")) {
                countB++;
            } else if (grade.getLetterGrade().equals("C")) {
                countC++;
            } else if (grade.getLetterGrade().equals("D")) {
                countD++;
            } else {
                countF++;
            }
        }

        String result = "Course Grade Distribution:\n";
        result += "A: " + countA + "\n";
        result += "B: " + countB + "\n";
        result += "C: " + countC + "\n";
        result += "D: " + countD + "\n";
        result += "F: " + countF + "\n";

        return result;
    }

    // Get student Grade Distribution
    public String getStudentGradeDistribution(String studentId) {
        int countA = 0;
        int countB = 0;
        int countC = 0;
        int countD = 0;
        int countF = 0;

        ArrayList<Grade> list = getGradesByStudentId(studentId);

        for (Grade grade : list) {
            if (grade.getLetterGrade().equals("A")) {
                countA++ ;
            } else if (grade.getLetterGrade().equals("B")) {
                countB++;
            } else if (grade.getLetterGrade().equals("C")) {
                countC++;
            } else if (grade.getLetterGrade().equals("D")) {
                countD++;
            } else {
                countF++;
            }
        }

        String result = "Student Grade Distribution:" + studentId +"\n";
        result += "A: " + countA + "\n";
        result += "B: " + countB + "\n";
        result += "C: " + countC + "\n";
        result += "D: " + countD + "\n";
        result += "F: " + countF + "\n";

        return result;
    }

    // Get the average grade for a specific course
    public double getAverageGradeForCourse(String courseId) {
        ArrayList<Grade> list = getGradesByCourseId(courseId);

        if(list.isEmpty())
            return -1;

        double sum = 0;
        int count = 0;

        for(Grade grade: list){
            sum += grade.getNumericGrade();
            count++;
        }

        return sum / count;
    }

    // Get a list of courses with the specified grade for a student
    public ArrayList<String> getCoursesByGrade(String studentId, String letterGrade) {
        ArrayList<String> list = new ArrayList<>();

        for (Grade grade : grades) {
            if (grade.getStudentId().equals(studentId) && grade.getLetterGrade().equalsIgnoreCase(letterGrade)){
                Course course = courseService.getCourseById(grade.getCourseId());

                if(course == null)
                    return null;

                String courseName = course.getName();
                list.add("Course: " + courseName + ", Grade: " + grade.getLetterGrade());
            }

        }
        return list;
    }
    private Grade getGradeByStudentAndCourseId(String studentId, String courseId) {
        for (Grade grade : grades) {
            if (grade.getStudentId().equals(studentId) && grade.getCourseId().equals(courseId)) {
                return grade;
            }
        }
        return null;
    }

    private ArrayList<Grade> getGradesByCourseId(String courseId) {
        ArrayList<Grade> list = new ArrayList<>();

        for (Grade grade : grades) {
            if (grade.getCourseId().equals(courseId)) {
                list.add(grade);
            }
        }
        return list;
    }

    private ArrayList<Grade> getGradesByStudentId(String studentId) {
        ArrayList<Grade> list = new ArrayList<>();

        for (Grade grade : grades) {
            if (grade.getStudentId().equals(studentId)) {
                list.add(grade);
            }
        }
        return list;
    }

}
