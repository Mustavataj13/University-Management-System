package com.university.controller;

import com.university.entities.Course;
import com.university.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    @PostMapping("/register")
    public String saveCourse(@RequestBody Course course){
            courseService.saveCourse(course);
            return "Course " + course.getName() + " saved succesfully";
    }

    @PutMapping("/update")
    public String updateCourse(
            @RequestParam Integer courseId,
            @RequestParam(required = false) String name
    ){

        try {
            courseService.updateCourse(courseId, name);
            return "New course name: " + name;
        } catch (Exception e) {
            return "Error";
        }
    }


    @PutMapping("/delete")
    public String deleteCourse(@RequestParam Integer courseId) {
        try {
            courseService.deleteCourse(courseId);
            return "Course deleted succesfully!";
        } catch (Exception e) {
            return "Error!";
        }
    }
}
