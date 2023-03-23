package com.university.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer courseId;

    @Column
    private String name;

    @Column
    private int code;

    @Column
    private int enabled;

    @Column
    private int department_id;
}
