package com.university.service;

import com.university.entities.Faculty;
import com.university.entities.Roles;
import com.university.entities.Student;
import com.university.entities.User;
import com.university.repository.FacultyRepository;
import com.university.repository.RolesRepository;
import com.university.repository.StudentRepository;
import com.university.repository.UserRepository;
import com.university.requests.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository usersRepository;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    FacultyRepository facultyRepository;

    public String saveUser(UserRequest request){
        //when saving it needs to find a student with x id and attach the username to that student
        Optional<Student> optionalStudent = studentRepository.findByEmail(request.getEmail());
        Optional<Faculty> optionalFaculty = facultyRepository.findByEmail(request.getEmail());
        Optional<User> optionalUser = usersRepository.findByUsername(request.getUsername());

        if (optionalUser.isPresent()){
            return "An user is already registered with this username!";
        }
        if (!optionalStudent.isPresent() && !optionalFaculty.isPresent()){
            return "No entities exist with this email!";
        }


        User user = new User();
        user.setUserId(request.getUserId());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setEnabled(request.getEnabled());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        user.setPassword(request.getPassword());

        usersRepository.save(user);
        Optional<User> optionalUser2 = usersRepository.findByEmail(request.getEmail());

        Roles role = new Roles();
        role.setRoleId(request.getUserId());
        role.setRole(request.getRoles());
        role.setUserId(optionalUser2.get().getUserId());
        role.setUsername(request.getUsername());

        rolesRepository.save(role);
        return "User " + user.getUsername() + " saved successfully with role " + role.getRole();
    }

    @Transactional
    public void deleteUser(String username){
        usersRepository.deleteUser(username);
    }

//    public String userData(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return  authentication.getName();
//    }
}
