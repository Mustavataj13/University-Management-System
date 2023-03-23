package com.university.service;

import com.university.entities.Course;
import com.university.entities.Enrollment;
import com.university.entities.Faculty;
import com.university.entities.Student;
import com.university.repository.CourseRepository;
import com.university.repository.EnrollmentRepository;
import com.university.repository.FacultyRepository;
import com.university.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Service
public class EnrollmentService {

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    StudentRepository studentRepository;

    public void enrollStudent(Integer studentId, Integer courseId, Integer facultyId){
        Optional<Course> course = courseRepository.findByCourseId(courseId);
        Optional<Faculty> faculty = facultyRepository.findById(facultyId);
        Optional<Student> student = studentRepository.findById(studentId);
        if (!student.isPresent()){
            throw new IllegalStateException("Student doesn't exist");
        }
        if (!course.isPresent()){
            throw new IllegalStateException("Course doesn't exist");
        }
        if (!faculty.isPresent()){
            throw new IllegalStateException("Faculty doesn't exist");
        }
        Enrollment enrollment = new Enrollment();
        enrollment.setStudentId(studentId);
        enrollment.setApproved(0);
        enrollment.setCourseId(courseId);
        enrollment.setFacultyId(facultyId);
        enrollmentRepository.save(enrollment);
    }

    @Transactional
    public void approveEnrollment(Integer enrollmentId){
        Optional<Enrollment> enrollment = enrollmentRepository.findById(enrollmentId);
        if(enrollment.isPresent()){
            enrollment.get().setApproved(1);
        }
        else {
            throw new IllegalStateException("No enrollment with this id exists");
        }
    }

    public void deleteEnrollment(Integer enrollmentId){
        enrollmentRepository.deleteById(enrollmentId);
    }

    @Transactional
    public void updateEnrollment(Integer enrollmentId, Integer courseId, Integer facultyId) {
        Optional<Enrollment> enrollment = enrollmentRepository.findById(enrollmentId);
        Optional<Course> course = courseRepository.findByCourseId(courseId);
        Optional<Faculty> faculty = facultyRepository.findById(facultyId);
        if (!enrollment.isPresent()){
            throw new IllegalStateException("Enrollment doesn't exist");
        }
        if (!course.isPresent()){
            throw new IllegalStateException("Course doesn't exist");
        }
        if (!faculty.isPresent()){
            throw new IllegalStateException("Faculty doesn't exist");
        }
        enrollment.get().setCourseId(courseId);
        enrollment.get().setFacultyId(facultyId);

    }
}
