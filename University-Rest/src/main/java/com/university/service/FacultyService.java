package com.university.service;

import com.university.entities.Faculty;
import com.university.entities.Student;
import com.university.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Service
public class FacultyService {

    @Autowired
    FacultyRepository facultyRepository;

    public void saveFaculty(Faculty faculty){
        Optional<Faculty> facultyOptional = facultyRepository
                .findByEmail(faculty.getEmail());
        if (facultyOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        facultyRepository.save(faculty);
    }

    @Transactional
    public void deleteFaculty(Integer facultyId){
        if (!facultyRepository.existsById(facultyId)) {
            throw new IllegalStateException("Course doesn't exist");
        }
        Optional<Faculty> faculty = facultyRepository.findById(facultyId);
        if (faculty.isPresent()){
            faculty.get().setEnabled(0);
        }
    }

    @Transactional
    public void updateFaculty(Integer facultyId, String name, String email) {
        Faculty faculty = facultyRepository.findById(facultyId).
                orElseThrow(() -> new IllegalStateException(
                        "Faculty with id " + facultyId + " does not exist!"));

        if (name != null && name.length() > 0 && !Objects.equals(faculty.getName(), name)) {
            faculty.setName(name);
        }
        if (email != null && email.length() > 0 && !Objects.equals(faculty.getEmail(), email)) {
            //check if email is taken, throw exception
            Optional<Faculty> optionalFaculty = facultyRepository.findByEmail(email);
            if (optionalFaculty.isPresent()) {
                throw new IllegalStateException("Email taken!");
            }
            faculty.setEmail(email);
        }
    }
}
