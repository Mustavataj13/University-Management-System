package com.university.controller;

import com.university.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {

    @Autowired
    EnrollmentService enrollmentService;
    @PutMapping("/update")
    public String updateEnrollment(@RequestParam Integer enrollmentId,
                                   @RequestParam(required = false) Integer courseId,
                                   @RequestParam(required = false) Integer facultyId){
        try {
            enrollmentService.updateEnrollment(enrollmentId, courseId, facultyId);
            return "Enrollment no. " + enrollmentId + " updated succesfully!";
        }catch (Exception e){
            return "Error!";
        }
    }

    @PutMapping("/approve")
    public String approveEnrollment(@RequestParam Integer enrollmentId){
        try {
            enrollmentService.approveEnrollment(enrollmentId);
            return "Enrollment no. "+ enrollmentId + " approved!";
        }catch (Exception e){
            return "Error!";
        }
    }
}
