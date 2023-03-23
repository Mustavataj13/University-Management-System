package com.university.controller;

import com.university.entities.Faculty;
import com.university.entities.Student;
import com.university.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    FacultyService facultyService;

    @PostMapping("/register")
    public String saveFaculty(@RequestBody Faculty faculty){
        try {
            facultyService.saveFaculty(faculty);
            return "Faculty " + faculty.getName() + " saved successfully";
        }catch (Exception e){
            return "Error!";
        }
    }

    @PutMapping("/update")
    public String updateFaculty(
            @RequestParam Integer facultyId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
    ){

        try {
            facultyService.updateFaculty(facultyId, name, email);
            return "Ok";
        } catch (Exception e) {
            return "Error";
        }
    }


//    public void deleteStudent(@PathVariable("studentId") Long studentId){
//        studentService.deleteStudent(studentId);
//    }
    @PutMapping("/delete")
    public String deleteFaculty(@RequestParam Integer facultyId) {
        try {
            facultyService.deleteFaculty(facultyId);
            return "Student with id " + facultyId + " deleted succesfully";
        } catch (Exception e) {
            return "Error!";
        }
    }
}
