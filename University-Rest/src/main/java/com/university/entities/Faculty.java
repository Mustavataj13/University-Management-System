package com.university.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(name = "faculty")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer facultyId;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private int phone;

    @Column
    private String address;

    @Column
    private Date dateOfBirth;

    @Column
    private int department_id;

    @Column
    private int enabled;
}
