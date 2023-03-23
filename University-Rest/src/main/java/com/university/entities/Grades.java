package com.university.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "grades")
public class Grades {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer gradeId;

    @Column
    private Integer studentId;

    @Column
    private Integer enrollmentId;

    @Column
    private Integer grade;
}
