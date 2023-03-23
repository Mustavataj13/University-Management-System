package com.university.controller;
import com.university.entities.Course;
import com.university.entities.Enrollment;
import com.university.entities.Grades;
import com.university.entities.Student;
import com.university.repository.CourseRepository;
import com.university.repository.EnrollmentRepository;
import com.university.repository.StudentRepository;
import com.university.service.EnrollmentService;
import com.university.service.GradeService;
import com.university.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    GradeService gradeService;

    @Autowired
    EnrollmentService enrollmentService;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @PostMapping("/register")
    public String saveStudent(@RequestBody Student student){
//        try {
//            studentService.saveStudent(student);
//            return "User " + student.getName() + " saved successfully";
//        }catch (Exception e){
//            return "Error!";
//        }
        studentService.saveStudent(student);
           return "User " + student.getName() + " saved successfully";
    }

    @PutMapping("/update")
    public String updateStudent(
            @RequestParam Integer studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
    ){

        try {
            studentService.updateStudent(studentId, name, email);
            return "Student updated successfully";
        } catch (Exception e) {
            return "Error";
        }
    }


    @PutMapping("/delete")
    public String deleteStudent(@RequestParam Integer studentId) {
        try {
            studentService.deleteStudent(studentId);
            return "Student with id " + studentId + " deleted succesfully";
        } catch (Exception e) {
            return "Error!";
        }
    }

    @PostMapping("/grade")
    public String gradeStudent(@RequestParam Integer studentId,
                               @RequestParam Integer enrollmentId,
                               @RequestParam Integer grade){
        if (grade<4 || grade>10){
            return "Grade not allowed";
        }
        try {
            gradeService.saveGrade(studentId, enrollmentId, grade);
            return "Grade saved successfully";
        }catch (Exception e){
            return "Error inserting grade!";
        }
    }

    @PostMapping("/enroll")
    public String enrollStudent(@RequestParam Integer studentId,
                                @RequestParam Integer courseId,
                                @RequestParam Integer facultyId){
        try {
            enrollmentService.enrollStudent(studentId, courseId, facultyId);
            return "Student with id" + studentId + " enrolled succesfully to course with id "
                    + courseId + " and faculty id " + facultyId;
        }catch (Exception e){
            return "Error!";
        }
    }//sets enabled to 0, /enrollment/approve sets it to 1

    @PostMapping("/disenroll")
    public String deleteEnrollment(Integer enrollmentId){
        try {
            enrollmentService.deleteEnrollment(enrollmentId);
            return "Enrollment no. " + enrollmentId + " deleted succesfully";
        }catch (Exception e){
            return "Error!";
        }
    }


    @GetMapping("/courses")
    public List<Optional<Course>> getCourses(@RequestParam String email){
        List<Enrollment> enrollmentList = enrollmentRepository.findByStudentId(
                studentRepository.findByEmail(email).get().getStudentId());

        List<Optional<Course>> courses = new ArrayList<>();
        for(int i = 0 ; i < enrollmentList.size(); i++){
            if(enrollmentList.get(i).getApproved()==0){
                continue;
            }
            courses.add(courseRepository.findByCourseId(enrollmentList.get(i).getCourseId()));
        }
        return courses;
    }

    @GetMapping("/grades")
    public Optional<List<Grades>> getGrades(@RequestParam String email){
//        Roles role = userService.getUserDetailsByUsername(auth.getName());
        Optional<Student> student = studentRepository.findByEmail(email);
        return gradeService.getGrades(student.get().getStudentId());
    }
}
