package com.university.repository;

import com.university.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

//    @Modifying
//    @Query("UPDATE Course c SET c.enabled=0 WHERE c.course_id= :course_id")
//    public void deleteCourse(@Param("course_id") Integer course_id);

    Optional<Course> findByName(String name);
    Optional<Course> findByCourseId(int courseId);
}
