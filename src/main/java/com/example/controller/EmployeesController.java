package com.example.controller;

import com.example.Views;
import com.example.model.entity.Employee;
import com.example.service.EmployeeService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    private EmployeeService employeeService;

    public EmployeesController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("")
    @JsonView(Views.Secured.class)
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }

}
