package com.university.service;

import com.university.entities.Grades;
import com.university.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService {
    @Autowired
    GradeRepository gradeRepository;

    public void saveGrade(Integer studentId, Integer enrollmentId, Integer grade){
        if (studentId == null || enrollmentId == null || grade == 0){
            throw new IllegalStateException("Illegal arguments");
        }else {
            Grades gradeObj = new Grades();
            gradeObj.setStudentId(studentId);
            gradeObj.setEnrollmentId(enrollmentId);
            gradeObj.setGrade(grade);
            gradeRepository.save(gradeObj);
        }
    }

    public Optional<List<Grades>> getGrades(Integer studentId){
        return gradeRepository.findByStudentId(studentId);
    }
}
