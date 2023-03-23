package com.university.controller;

import com.university.entities.*;
import com.university.repository.CourseRepository;
import com.university.repository.EnrollmentRepository;
import com.university.repository.StudentRepository;
import com.university.repository.UserRepository;
import com.university.requests.UserRequest;
import com.university.service.GradeService;
import com.university.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    GradeService gradeService;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;
    @PostMapping("/register")
    public String registerUser(@RequestBody UserRequest user){
        return userService.saveUser(user);
    }

    @GetMapping("/grades")
    public Optional<List<Grades>> getGrades(Principal auth){
        Optional<User> user = userRepository.findByUsername(auth.getName());
        Optional<Student> student = studentRepository.findByEmail(user.get().getEmail());
        return gradeService.getGrades(student.get().getStudentId());
    }

    @GetMapping("/courses")
    public List<Optional<Course>> getCourses(Principal auth){
        Optional<User> user = userRepository.findByUsername(auth.getName());
        Optional<Student> student = studentRepository.findByEmail(user.get().getEmail());
        List<Enrollment> enrollmentList = enrollmentRepository.findByStudentId(student.get().getStudentId());

        List<Optional<Course>> courses = new ArrayList<>();
        for(int i = 0 ; i < enrollmentList.size(); i++){
            courses.add(courseRepository.findByCourseId(enrollmentList.get(i).getCourseId()));
        }
        return courses;
    }

    @PutMapping("/delete")
    public String deleteUser(@RequestParam String username){
        try {
            userService.deleteUser(username);
            return "User " + username + " deleted succesfully!";
        }catch (Exception e){
            return "Error!";
        }
    }
}
