package com.example.service;

import com.example.model.entity.Employee;
import com.example.model.entityrepository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees(){

        List<Employee> list = new ArrayList<>();
        employeeRepository.findAll().forEach(list::add);
        return list;

    }
}
