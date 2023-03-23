package com.university.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "department")
public class Department {
    @Id
    private Integer departmentId;

    @Column
    private String name;

    @Column
    private int code;

    @Column
    private int enabled;
}
