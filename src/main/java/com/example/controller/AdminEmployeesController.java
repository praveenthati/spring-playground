package com.example.controller;

import com.example.model.entity.Employee;
import com.example.service.EmployeeDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/admin")
@RestController
public class AdminEmployeesController {

    private EmployeeDetailsService employeeDetailsService;

    public AdminEmployeesController(EmployeeDetailsService employeeDetailsService) {
        this.employeeDetailsService = employeeDetailsService;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees(HttpServletRequest request) {

        return employeeDetailsService.getAllEmployees();
    }

}
