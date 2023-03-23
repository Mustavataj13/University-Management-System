package com.university.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer studentId;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private int phone;

    @Column
    private String address;

    @Column
    private String date_of_birth;

    @Column
    private int department_id;

    @Column
    private int enabled;
}
