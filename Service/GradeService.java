package com.example.learning_management_system.Service;

import com.example.learning_management_system.Model.Grade;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GradeService {

    ArrayList<Grade> grades = new ArrayList<>();

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

        grade.setGrade(updatedGrade.getGrade());

        return true;
    }

    public boolean deleteGrade(String studentId, String courseId) {
        Grade grade = getGradeByStudentAndCourseId(studentId, courseId);
        if (grade == null)
            return false;

        grades.remove(grade);
        return true;
    }

    private Grade getGradeByStudentAndCourseId(String studentId, String courseId) {
        for (Grade grade : grades) {
            if (grade.getStudentId().equals(studentId) && grade.getCourseId().equals(courseId)) {
                return grade;
            }
        }
        return null;
    }
}
