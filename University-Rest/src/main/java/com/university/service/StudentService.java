package com.university.service;

import com.university.entities.Course;
import com.university.entities.Student;
import com.university.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public void saveStudent(Student student){
        Optional<Student> studentOptional = studentRepository
                .findByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

//    public void deleteStudet(Integer studentId){
//        if (!studentRepository.existsById(studentId)) {
//            throw new IllegalStateException("Student doesn't exist");
//        }
//        studentRepository.deleteById(studentId);
//    }

    @Transactional
    public void deleteStudent(Integer studentId){
        if (!studentRepository.existsById(studentId)) {
            throw new IllegalStateException("Course doesn't exist");
        }
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()){
            student.get().setEnabled(0);
        }
    }

    @Transactional
    public void updateStudent(Integer studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).
                orElseThrow(() -> new IllegalStateException(
                        "Student with id " + studentId + " does not exist!"));

        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }
        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            //check if email is taken, throw exception
            Optional<Student> optionalStudent = studentRepository.findByEmail(email);
            if (optionalStudent.isPresent()) {
                throw new IllegalStateException("Email taken!");
            }
            student.setEmail(email);
        }
    }
}
