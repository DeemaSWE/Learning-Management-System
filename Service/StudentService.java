package com.example.learning_management_system.Service;

import com.example.learning_management_system.Model.Course;
import com.example.learning_management_system.Model.Student;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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


    // Get Top 10 Students with the highest gpa for a specified major
    public List<Student> getTopStudents(String major) {
        ArrayList<Student> list = new ArrayList<>();

        for (Student student : students) {
            if (student.getMajor().equalsIgnoreCase(major)) {
                list.add(student);
            }
        }

        if(list.isEmpty())
            return null;

        list.sort(Comparator.comparingDouble(Student::getGpa).reversed());

        if (list.size() >= 10)
            return list.subList(0, 10);

        return list;

    }

    // Calculates the average gpa for students for a specified major
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
            return -1;

        return sum / count;
    }

    // Get a list of students who have exceeded the expected number of years for their major and are graduating late
    public ArrayList<Student> getLateStudent(String major, int years) {
        ArrayList<Student> list = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();

        for (Student student : students) {
            if (student.getMajor().equalsIgnoreCase(major) && student.getEnrollmentDate().plusYears(years).isBefore(currentDate) && !student.isGraduated())
                list.add(student);
        }
        return list;
    }

    // Get a list of students within the specified age range
    public ArrayList<Student> getStudentsByAgeRange(Integer minAge, Integer maxAge) {
        ArrayList<Student> list = new ArrayList<>();

        for (Student student : students) {
            if (student.getAge() >= minAge && student.getAge() <= maxAge)
                list.add(student);
        }

        return list;
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

