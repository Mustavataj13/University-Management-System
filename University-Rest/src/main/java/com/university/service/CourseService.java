package com.university.service;

import com.university.entities.Course;
import com.university.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    public void saveCourse(Course course){
        Optional<Course> course1 = courseRepository.findByName(course.getName());
        if (course1.isPresent()) {
            throw new IllegalStateException("Course name taken");
        }
        courseRepository.save(course);
    }


    @Transactional
    public void deleteCourse(Integer courseId){
        if (!courseRepository.existsById(courseId)) {
            throw new IllegalStateException("Course doesn't exist");
        }
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isPresent()){
            course.get().setEnabled(0);
        }
    }

    @Transactional
    public void updateCourse(Integer courseId, String courseName) {
        Course course = courseRepository.findById(courseId).
                orElseThrow(() -> new IllegalStateException(
                        "Student with id " + courseId + " does not exist!"));

        if (courseName != null && courseName.length() > 0 && !Objects.equals(course.getName(), courseName)) {
            course.setName(courseName);
        }
    }
}
