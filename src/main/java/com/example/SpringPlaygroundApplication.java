package com.example;

import com.example.model.entity.Employee;
import com.example.model.entityrepository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class SpringPlaygroundApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringPlaygroundApplication.class, args);
	}

//	@Bean
//	@Profile("default")
//	public CommandLineRunner seedData(EmployeeRepository employeeRepository) {
//		return (args) -> {
//			employeeRepository.deleteAll();
//			Employee employee = new Employee();
//			employee.setName("Employee");
//			employee.setSalary(24);
//			employeeRepository.save(employee);
//		};
//
//	}
}
