package com.university.repository;

import com.university.entities.Grades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<Grades, Integer> {
    Optional<List<Grades>> findByStudentId(int studentId);
}
