package com.example.service;

import com.example.model.entity.Employee;
import com.example.model.entityrepository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeDetailsService implements UserDetailsService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByUsername(username);
        if (employee == null) throw new UsernameNotFoundException("Username " + username + " not found");
        return employee;
    }

    public List<Employee> getAllEmployees(){

        List<Employee> list = new ArrayList<>();
        employeeRepository.findAll().forEach(list::add);
        return list;

    }
}
