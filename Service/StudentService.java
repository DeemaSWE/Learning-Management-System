package com.example.learning_management_system.Service;

import com.example.learning_management_system.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class StudentService {

    ArrayList<Student> students = new ArrayList<>();

    public ArrayList<Student> getStudents() {

        return students;
    }

    public void addStudent(Student student) {

        students.add(student);
    }

    public boolean updateStudent(String id, Student updatedStudent){
        Student student = getStudentById(id);
        if(student == null)
            return false;

        students.set(students.indexOf(student), updatedStudent);
        return true;
    }

    public boolean deleteStudent(String id){
        Student student = getStudentById(id);
        if(student == null)
            return false;

        students.remove(student);
        return true;
    }

    // Get a list of students that will graduate this semester
    public ArrayList<Student> getGraduatingStudents(String major) {
        ArrayList<Student> graduatingStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getMajor().equalsIgnoreCase(major) && student.getCurrentLevel() == 8) {
                graduatingStudents.add(student);
            }
        }
        return graduatingStudents;
    }


    // Get 10 top performing students for a specified major based on the gpa
    public List<Student> getTopStudents(String major) {
        ArrayList<Student> topStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getMajor().equalsIgnoreCase(major)) {
                topStudents.add(student);
            }
        }

        topStudents.sort(Comparator.comparingDouble(Student::getGpa).reversed());

        return topStudents.subList(0, 10);
    }

    // Get the average gpa for a specified major
    public double getAverageGpa(String major) {
        int count = 0;
        double sum = 0;

        for (Student student : students) {
            if (student.getMajor().equalsIgnoreCase(major)) {
                sum += student.getGpa();
                count++;
            }
        }

        if(count == 0)
            return 0;

        return sum / count;
    }
    public Student getStudentById(String studentId) {
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }
}

