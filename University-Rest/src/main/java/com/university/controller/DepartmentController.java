package com.university.controller;

import com.university.entities.Department;
import com.university.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;
    @PostMapping("/save")
    public String saveDepartment(@RequestBody Department department){
        try {
            departmentService.saveDepartment(department);
            return "Department saved succesfully";
        }catch (Exception e){
            return "Error!";
        }
    }
}
