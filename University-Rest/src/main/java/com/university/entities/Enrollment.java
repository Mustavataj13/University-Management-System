package com.university.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(name = "enrollment")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer enrollmentId;

    @Column
    private int studentId;

    @Column
    private int courseId;

    @Column
    private int facultyId;

    @Column
    private int approved;
}
