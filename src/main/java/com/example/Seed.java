package com.example;

import com.example.model.entity.Employee;
import com.example.model.entityrepository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("default")
public class Seed {

    @Bean
    public CommandLineRunner seedData(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {

        return (args) -> {
            employeeRepository.deleteAll();
            Employee employee = new Employee();
            employee.setName("Employee");
            employee.setSalary(24);
            employee.setUsername("employee");
            employee.setPassword(passwordEncoder.encode("my-employee-password"));
            employee.setRole("EMPLOYEE");
            employeeRepository.save(employee);

            Employee boss = new Employee();
            boss.setName("Bossy Boss");
            boss.setSalary(24);
            boss.setUsername("boss");
            boss.setPassword(passwordEncoder.encode("my-boss-password"));
            boss.setRole("MANAGER");
            employeeRepository.save(boss);
        };
    }
}
